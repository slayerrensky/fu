package fu.netzsys.ex3.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sun.org.apache.xpath.internal.functions.Function;

public class RecommenderSystem {

	double qSum = 0;
	ArrayList<Udata> ratedIDataU1 = new ArrayList<Udata>();
	ArrayList<Udata> ratedIDataU2 = new ArrayList<Udata>();

	/**
	 * Calculate Pearson score
	 * @param user1
	 * @param user2
	 * @return
	 */
	public double getSimilarityFromUsers(Uuser user1, Uuser user2) {

		ArrayList<Uitem> similarItems = getSimilarItems(user1, user2);
		if (similarItems == null)
			return -1;
		if (similarItems.size() == 0)
			return 0;

		// Addiere Ratings zu jedem Item
		double sum1 = 0;
		double sum1Sq = 0;
		double sum2 = 0;
		double sum2Sq = 0;

		ArrayList<Udata> User1Data = getDataByItemList(similarItems,ratedIDataU1);
		ArrayList<Udata> User2Data = getDataByItemList(similarItems,ratedIDataU2);
		double mittelw1 = mittelw(User1Data);
		double mittelw2 = mittelw(User2Data);
		qSum = qSum(User1Data,User2Data,mittelw1,mittelw2);
		
		for (int i = 0; i < similarItems.size(); i++) {
			sum1 = getSumRatingOfItemAndUser(similarItems.get(i), user1, mittelw1);
			sum1Sq += sum1 * sum1;
			sum2 = getSumRatingOfItemAndUser(similarItems.get(i), user2, mittelw2);
			sum2Sq += sum2 * sum2;
		}

		double den = Math.sqrt((sum1Sq * sum2Sq));
		if(den == 0 )
			return 0;
		else
			return qSum / den;
				
	}
	
	
	/**
	 * Berechnet die Summe aller Gemeinsamen Bewerteten items der User (R)
	 * Die beiden Array müssen die selbe länge haben
	 * @param user1
	 * @param user2
	 * @return
	 */
	private double qSum(ArrayList<Udata> user1, ArrayList<Udata> user2, double mitteluser1, double mitteluser2) {
		double n = 0;
		if (user1.size() == user2.size())
		{
			for (int i = 0; i<user1.size(); i++)
			{
				n += (user1.get(i).getRating() - mitteluser1) * (user2.get(i).getRating()- mitteluser2);
			}
		}
		return n;
	}
	
	public double mittelw(ArrayList<Udata> user)
	{
		double mittel = 0;
		for (int i = 0; i<user.size(); i++)
		{
			mittel = user.get(i).getRating();
		}
		mittel = mittel/ user.size();
		return mittel;
	}
	
	/**
	 * Holt sich die Udata die mit den Userdata und der Itemliste übereinstimmen
	 * @param item
	 * @return
	 */
	private ArrayList<Udata> getDataByItemList(ArrayList<Uitem> item, ArrayList<Udata> userdata)
	{
		ArrayList<Udata> data = new ArrayList<Udata>();
		
		for (Uitem it : item)
		{
			for (int i = 0; i < userdata.size(); i++) {
				if (userdata.get(i).getItem() == it )
				{
					data.add(Udata.list.get(i));
					break;
				}
			}
		}
		
		return data;
	}
	
	// TODO: Performanceverbesserung durch direkte Abfrage getRatedItems()
	public double getSumRatingOfItemAndUser(Uitem item, Uuser u,double mittelw) {

		double sum = 0;
		for (int i = 0; i < Udata.list.size(); i++) {
			if (Udata.list.get(i).getUser() == u)
				if (Udata.list.get(i).getItem() == item)
					sum += Udata.list.get(i).getRating() - mittelw;
		}
		return sum;
	}

	/***
	 * Berechnet die Menge der Items die U1 und U2 gemeinsam haben.
	 * @param user1
	 * @param user2
	 * @return
	 */
	public ArrayList<Uitem> getSimilarItems(Uuser user1, Uuser user2) {
		ratedIDataU1 = getRatedData(user1);
		ratedIDataU2 = getRatedData(user2);
		
		ArrayList<Uitem> ratedItemsU1 = Udata.getAllItems(ratedIDataU1);
		ArrayList<Uitem> ratedItemsU2 = Udata.getAllItems(ratedIDataU2);
		
		ArrayList<Uitem> similar = new ArrayList<Uitem>();

		int cycle = ratedItemsU1.size() > ratedItemsU2.size() ? ratedItemsU2
				.size() : ratedItemsU1.size();
		for (int i = 0; i < cycle; i++) {
			if (ratedItemsU1.contains(ratedItemsU2.get(i))) {
				similar.add(ratedItemsU2.get(i));
			}
		}

		return similar; // eigentlich könnte man hier qsum ausführen
	}
	
	private double qsumEasy(Udata x, Udata y)
	{
		return x.getRating() * y.getRating();
	}
	

	public ArrayList<Udata> getRatedData(Uuser user) {
		ArrayList<Udata> ratedItems = new ArrayList<Udata>();
		for (int i = 0; i < Udata.list.size(); i++) {
			if (Udata.list.get(i).getUser() == user) {
				ratedItems.add(Udata.list.get(i));
			}
		}
		return ratedItems;
	}
	
	public ArrayList<Uuser> getAllSimilarItems(Uuser user1, ArrayList<Uuser> users, double graterThan, int max){
		System.out.println("calculate sim...");
		int c =0;
		ArrayList<Uuser> ret = new ArrayList<Uuser>();
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for(int i=0;i<users.size();i++){
			c++;
			if(c>=100){
				c=0;
				System.out.println("100 user computed");
			}
			Uuser otherUser = users.get(i);
			double sim = getSimilarityFromUsers(user1, otherUser);
			if(sim > graterThan){
				map.put(i,sim);
			}
		}
		int counter = 0;
	    for(Map.Entry<Integer, Double> entry : map.entrySet()) {
	    	ret.add(users.get(entry.getKey()));
	    	counter++;
	    	if(counter >=max){
	    		break;
	    	}
	    }
	    System.out.println("found: "+ret.size()+" sim users (max:"+max+")");
		return ret;
	}
}

class MapUtil
{
    public static <K, V extends Comparable<? super V>> Map<K, V> 
        sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
            new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}