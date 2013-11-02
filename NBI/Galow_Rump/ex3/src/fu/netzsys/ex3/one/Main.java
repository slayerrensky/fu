package fu.netzsys.ex3.one;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// File Testdata -> Korrelationskoeffizient ist 0,88
		String pathToDir = new File("data/").getAbsoluteFile() + "/";
		RecommenderSystem r = new RecommenderSystem(pathToDir);

		ArrayList<SimilarUser> similarUserList = r.getMaxSimilarUser(
				Uuser.list.get(0), 0.8, 50);

		Collections.sort(similarUserList);
		// How much found?
		System.out.println("found: " + similarUserList.size()
				+ " sim users (max:" + 50 + ")");

		// r.getRecommendedItemsByUser(Uuser.list.get(0), 100);
		// Print all
		for (int i = 0; i < similarUserList.size(); i++) {
			SimilarUser u = similarUserList.get(i);

			double sim = (double) Math.round(u.getSimilarity() * 1000) / 1000;
			System.out.println(i + 1 + ": similarity between "
					+ u.getUser1().getId() + " and " + u.getUser2().getId()
					+ " is " + sim);
		}

		// programm für 3.3 alle filme anzeigen/ als liste
		// einloggen mit bestimmter user id
		// meine bewerte filme
		// seite für ein film inkl. bewertung
		// seite mit 20 interessanten filmen <------ hierfür fehlt die
		// berechnung
	}
}