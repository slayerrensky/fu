package fu.netzsys.ex3.one;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String pathToDir = "/Users/larswillrich/DropBox/Dropbox/UniPrivat/FU/Sem1/Netzbasierte Informationssysteme/Exercise/3/ml-100k/";

		try {
			Ugenre.fillList(pathToDir); // Keine Referenzen
			Uitem.fillList(pathToDir);// Keine Referenzen
			Uoccupation.fillList(pathToDir); // Keine Referenzen

			Uuser.fillList(pathToDir); // Referenz zu Uoccupation
			Udata.fillList(pathToDir); // Referenz zu Uuser und Uitem
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Print lists
		// Ugenre.printList();
		// Uitem.printList();
		// Uoccupation.printList();
		// Uuser.printList();
		// Udata.printList();
		// System.out.println(Udata.list.get(0).getItem().getTitle()
		// + " hat das Rating -> " + Udata.list.get(0).getRating());
		RecommenderSystem r = new RecommenderSystem();
		///166 3
		double similarityFromUsers = r.getSimilarityFromUsers(
				Uuser.list.get(2), Uuser.list.get(165));
		System.out.println(similarityFromUsers);
	}
}
