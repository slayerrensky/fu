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

	int qSum = 0;
	ArrayList<Udata> ratedIDataU1 = new ArrayList<Udata>();
	ArrayList<Udata> ratedIDataU2 = new ArrayList<Udata>();

	public double getSimilarityFromUsers(Uuser user1, Uuser user2) {

		ArrayList<Uitem> similarItems = getSimilarItems(user1, user2);
		if (similarItems == null)
			return -1;
		if (similarItems.size() == 0)
			return 0;

		// Addiere Ratings zu jedem Item
		int sum1 = 0;
		int sum1Sq = 0;
		int sum2 = 0;
		int sum2Sq = 0;
		for (int i = 0; i < similarItems.size(); i++) {
			sum1 = getSumRatingOfItemAndUser(similarItems.get(i), user1);
			sum1Sq = sum1 * sum1;
			sum2 = getSumRatingOfItemAndUser(similarItems.get(i), user2);
			sum2Sq = sum2 * sum2;
		}

		// Addiere zu jedem Item beide Ratings multipliziert von beiden Usern
		// ->qSum
		// TODO
		//
		// Es sollten hier zwei Arrays vorhanden sein mit Userdaten von den usern
		// Diese daten enthalten nur die daten welche die User gemeinsam haben.
		// Das ist vorraussetzung für die qSum funktion
		qSum = qSum(ratedIDataU1, ratedIDataU2);

		// Calculate Pearson score
		double n = similarItems.size();
		double num = qSum - (((double) (sum1 * sum2)) / n);
		double den = Math.sqrt((sum1Sq - Math.pow(sum1, 2) / n)
				* (sum2Sq - Math.pow(sum2, 2) / n));
		if (den == 0)
			return 0;
		return num / den;
	}
	
	
	/**
	 * Berechnet die Summe aller Gemeinsamen Bewerteten items der User (R)
	 * Die beiden Array müssen die selbe länge haben
	 * @param user1
	 * @param user2
	 * @return
	 */
	private int qSum(ArrayList<Udata> user1, ArrayList<Udata> user2) {
		int n = 0;
		if (user1.size() != user2.size())
		{
			for (int i = 0; i<user1.size(); i++)
			{
				n += user1.get(i).getRating() * user2.get(i).getRating();
			}
		}
		return n;
	}


	
	// TODO: Performanceverbesserung durch direkte Abfrage getRatedItems()
	public int getSumRatingOfItemAndUser(Uitem item, Uuser u) {

		int sum = 0;
		for (int i = 0; i < Udata.list.size(); i++) {
			if (Udata.list.get(i).getUser() == u)
				if (Udata.list.get(i).getItem() == item)
					sum += Udata.list.get(i).getRating();
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