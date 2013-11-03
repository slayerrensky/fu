package fu.netzsys.ex3.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class RecommenderSystem {

	double qSum = 0;

	// ArrayList<Udata> ratedIDataU1 = new ArrayList<Udata>();
	// ArrayList<Udata> ratedIDataU2 = new ArrayList<Udata>();

	/**
	 * Calculate Pearson score
	 * 
	 * @param user1
	 * @param user2
	 * @return
	 */

	public RecommenderSystem(String pathToDir) {
		try {
			Ugenre.fillList(pathToDir); // Keine Referenzen
			Uitem.fillList(pathToDir);// Keine Referenzen
			Uoccupation.fillList(pathToDir); // Keine Referenzen

			Uuser.fillList(pathToDir); // Referenz zu Uoccupation
			Udata.fillList(pathToDir); // Referenz zu Uuser und Uitem
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<RelevantRatedItemWeigth> getRecommendedItemsByUser(Uuser user, int maxItems) {
		return getRelevantItems(user, getMaxSimilarUser(user, -1, 100));
	}

	public double getSimilarFromUsers(Uuser user1, Uuser user2) {

		if (user1 == user2)
			return 1;
		ArrayList<Uitem> similarItems = getSimilarItems(user1, user2);
		if (similarItems == null)
			return -1;
		if (similarItems.size() == 0)
			return 0;

		// Addiere Ratings zu jedem Item
		double sum1Sq = 0;
		double sum2Sq = 0;

		ArrayList<Udata> User1Data = getDataByItemList(similarItems,
				user1.getMyRatings());
		ArrayList<Udata> User2Data = getDataByItemList(similarItems,
				user2.getMyRatings());

		double arithmeticMean1 = calcArithmeticMean(User1Data);
		double arithmeticMean2 = calcArithmeticMean(User2Data);
		qSum = getSumOfSquares(User1Data, User2Data, arithmeticMean1,
				arithmeticMean2);

		for (int i = 0; i < similarItems.size(); i++) {
			sum1Sq += getPowerOfUserRatingMinusArithmeticMean(
					similarItems.get(i), user1, arithmeticMean1);
			sum2Sq += getPowerOfUserRatingMinusArithmeticMean(
					similarItems.get(i), user2, arithmeticMean2);
		}

		double den = Math.sqrt((sum1Sq * sum2Sq));
		if (den == 0)
			return 0;
		else
			return qSum / den;

	}

	/**
	 * Berechnet die Summe aller Gemeinsamen Bewerteten items der User (R) Die
	 * beiden Array mï¿½ssen die selbe lï¿½nge haben
	 * 
	 * @param user1
	 * @param user2
	 * @return
	 */
	private double getSumOfSquares(ArrayList<Udata> user1,
			ArrayList<Udata> user2, double mitteluser1, double mitteluser2) {
		double n = 0;
		if (user1.size() == user2.size()) {
			for (int i = 0; i < user1.size(); i++) {
				n += (user1.get(i).getRating() - mitteluser1)
						* (user2.get(i).getRating() - mitteluser2);
			}
		}
		return n;
	}

	public double calcArithmeticMean(ArrayList<Udata> user) {
		double mittel = 0;
		for (int i = 0; i < user.size(); i++)
			mittel += user.get(i).getRating();
		mittel = mittel / user.size();
		return mittel;
	}

	/**
	 * Holt sich die Udata die mit den Userdata und der Itemliste ï¿½bereinstimmen
	 * 
	 * @param similarItem
	 * @return
	 */
	private ArrayList<Udata> getDataByItemList(ArrayList<Uitem> similarItem,
			ArrayList<Udata> userdata) {
		CopyOnWriteArrayList<Udata> data = new CopyOnWriteArrayList<Udata>();

		for (Uitem it : similarItem) {
			for (int i = 0; i < userdata.size(); i++) {
				if (userdata.get(i).getItem() == it) {
					data.add(userdata.get(i));
					break;
				}
			}
		}

		ArrayList<Udata> arrayList = new ArrayList<Udata>(data);
		return arrayList;
	}

	public double getPowerOfUserRatingMinusArithmeticMean(Uitem item, Uuser u,
			double mittelw) {

		for (int i = 0; i < Udata.list.size(); i++) {
			if (Udata.list.get(i).getUser() == u)
				if (Udata.list.get(i).getItem() == item)
					return Math.pow(Udata.list.get(i).getRating() - mittelw, 2);
		}
		return 0;
	}

	/***
	 * Berechnet die Menge der Items die U1 und U2 gemeinsam haben.
	 * 
	 * @param user1
	 * @param user2
	 * @return
	 */
	public ArrayList<Uitem> getSimilarItems(Uuser user1, Uuser user2) {

		ArrayList<Uitem> ratedItemsU1 = Udata.getAllItems(user1.getMyRatings());
		ArrayList<Uitem> ratedItemsU2 = Udata.getAllItems(user2.getMyRatings());

		ArrayList<Uitem> similar = new ArrayList<Uitem>();

		int cycle = ratedItemsU1.size() > ratedItemsU2.size() ? ratedItemsU2
				.size() : ratedItemsU1.size();
		for (int i = 0; i < cycle; i++) {
			if (ratedItemsU1.contains(ratedItemsU2.get(i))) {
				similar.add(ratedItemsU2.get(i));
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

	public ArrayList<SimilarUser> getMaxSimilarUser(Uuser user1,
			double similarityGraeterThan, int max) {
		ArrayList<SimilarUser> similarUserList = new ArrayList<SimilarUser>();

		ArrayList<Uuser> users = Uuser.list;
		for (int i = 0; i < users.size(); i++) {
			if (similarUserList.size() == 50)
				break;

			if (i % 100 == 0) {
				System.out.println(((double) Math.round(((double) i)
						/ users.size() * 100 * 100) / 100)
						+ " % computed");
			}
			Uuser compareUser = users.get(i);

			double sim = getSimilarFromUsers(user1, compareUser);
			if (sim > similarityGraeterThan) {
				similarUserList.add(new SimilarUser(user1, compareUser, sim));
			}
		}
		return similarUserList;
	}

	public RelevantRatedItemWeigth isRelevant(Uitem item, ArrayList<SimilarUser> relevantUsers,
			double threshold) {
		if (relevantUsers.size() == 0)
			return new RelevantRatedItemWeigth(item, 0.0, true);
		// for each user: item rating * r
		double denominator = 0;
		double numerator = 0;
		for (SimilarUser sUser : relevantUsers) {
			Udata ratingUser2 = sUser.getUser2().getUDataByItem(item);
			if(ratingUser2 != null){
				denominator += sUser.getSimilarity()
						* ratingUser2.getRating();
				numerator += sUser.getSimilarity();
			}
		}
		Double rating = denominator / numerator;
		if (rating > threshold){
			return new RelevantRatedItemWeigth(item, rating, true);
		}else{
			return new RelevantRatedItemWeigth(item, rating, false);
		}
	}

	public ArrayList<RelevantRatedItemWeigth> getRelevantItems(Uuser me,
			ArrayList<SimilarUser> relevanttUsers) {
		// liste generieren, die alle filme der relevanten user beinhaltet
		ArrayList<Uitem> allRelevantItems = new ArrayList<Uitem>();

		for (SimilarUser user : relevanttUsers) {
			ArrayList<Udata> myRatings = user.getUser2().getMyRatings();
			ArrayList<Udata> userDataList = myRatings;
			for (Udata data : userDataList) {
				if (!allRelevantItems.contains(data.getItem())) {
					allRelevantItems.add(data.getItem());
					// list +
				}// else rating anpassen
			}
		}

		ArrayList<Udata> ratedData = me.getMyRatings();
		ArrayList<Uitem> ratedItems = new ArrayList<Uitem>();
		for (int i = 0; i < ratedData.size(); i++) {
			ratedItems.add(ratedData.get(i).getItem());
		}
		allRelevantItems.removeAll(ratedItems);

		ArrayList<RelevantRatedItemWeigth> relevantItems = new ArrayList<RelevantRatedItemWeigth>();
		for (int i = 0; i < ratedItems.size(); i++) {
			RelevantRatedItemWeigth rUI = isRelevant(ratedItems.get(i), relevanttUsers, 0.5); 
			if (rUI.relevant)
				relevantItems.add(rUI);
		}
		Collections.sort(relevantItems);
		return relevantItems;
		// bewertung errechnen nach formel Seite 42 3-IRFiltering.pdf
		// (Vorlesung)
		// gegeben: alle wichtige filme
		// gesucht: filmrating errechent aus den usern und der sim()
		// for über alle ratings (Udata) gewicht speichern
		// RelevatRatedMovieWeigth

		// sortieren -- am besten mit gewichtung (filme mit vielen bewertungen
		// der relevaten user, sind besser)
	}
}

class SimilarUser implements Comparable<SimilarUser> {
	Uuser user1 = null;
	Uuser user2 = null;
	double similarity = -2;

	public SimilarUser(Uuser user1, Uuser user2, double similarity) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.similarity = similarity;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public Uuser getUser1() {
		return user1;
	}

	public void setUser1(Uuser user1) {
		this.user1 = user1;
	}

	public Uuser getUser2() {
		return user2;
	}

	public void setUser2(Uuser user2) {
		this.user2 = user2;
	}

	@Override
	public int compareTo(SimilarUser o) {
		if (o.getSimilarity() > this.getSimilarity())
			return 1;
		else if (o.getSimilarity() == this.getSimilarity())
			return 0;
		return -1;
	}
}

class MapUtil {
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(
			Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}