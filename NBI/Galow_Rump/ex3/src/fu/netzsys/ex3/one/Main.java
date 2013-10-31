package fu.netzsys.ex3.one;

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
		 Uuser.printList();
		// Udata.printList();
//		System.out.println(Udata.list.get(0).getItem().getTitle()
//				+ " hat das Rating -> " + Udata.list.get(0).getRating());
	}
}
