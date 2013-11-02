package fu.netzsys.ex3.one;

import java.io.File;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String pathToDir = new File("testData/").getAbsoluteFile() + "/";
		RecommenderSystem r = new RecommenderSystem(pathToDir);

		ArrayList<SimilarUser> similarUserList = r.getMaxSimilarUser(
				Uuser.list.get(105), Uuser.list, 0.9, 50);

		// How much found?
		System.out.println("found: " + similarUserList.size()
				+ " sim users (max:" + 50 + ")");

		// Print all
		for (int i = 0; i < similarUserList.size(); i++) {
			SimilarUser u = similarUserList.get(i);

			System.out.println(i + 1 + ": similarity between "
					+ u.getUser1().getId() + " and " + u.getUser2().getId()
					+ " is " + (double) Math.round(u.getSimilarity() * 1000)
					/ 1000);
		}
		
		// programm für 3.3 alle filme anzeigen/ als liste
		// einloggen mit bestimmter user id
		// meine bewerte filme
		// seite für ein film inkl. bewertung
		// seite mit 20 interessanten filmen <------ hierfür fehlt die berechnung
		
		
	}
}
