package fu.netzsys.ex3.one;

import java.util.ArrayList;

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
		qSum(ratedIDataU1, ratedIDataU1);

		// Calculate Pearson score
		double n = similarItems.size();
		double num = qSum - (((double) (sum1 * sum2)) / n);
		System.out.println(num);
		double den = Math.sqrt((sum1Sq - Math.pow(sum1, 2) / n)
				* (sum2Sq - Math.pow(sum2, 2) / n));
		if (den == 0)
			return 0;
		System.out.println(den);
		return num / den;
	}

	private int qSum(ArrayList<Udata> user1, ArrayList<Udata> user2) {
		// qSum += ratedItemsU1.get(i).getRating() *
		// ratedItemsU2.get(i).getRating();
		return 0;
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

	public ArrayList<Uitem> getSimilarItems(Uuser user1, Uuser user2) {
		ratedIDataU1 = getRatedData(user1);
		ratedIDataU2 = getRatedData(user2);
		
		ArrayList<Uitem> ratedItemsU1 = Udata.getAllItems(ratedIDataU1);
		ArrayList<Uitem> ratedItemsU2 = Udata.getAllItems(ratedIDataU1);
		
		ArrayList<Uitem> similar = new ArrayList<Uitem>();

		int cycle = ratedItemsU1.size() > ratedItemsU2.size() ? ratedItemsU2
				.size() : ratedItemsU1.size();
		for (int i = 0; i < cycle; i++) {
			if (ratedItemsU1.contains(ratedItemsU2.get(i))) {
				System.out.println("treffer!");
				similar.add(ratedItemsU1.get(i));
			}
		}

		return similar;
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
}
