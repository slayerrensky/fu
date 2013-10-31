package fu.netzsys.ex3.one;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String path = "/Users/larswillrich/DropBox/Dropbox/UniPrivat/FU/Sem1/Netzbasierte Informationssysteme/Exercise/3/ml-100k/u.genre";
		ArrayList<Ugenre> allData = null;
		try {
			allData = Ugenre.getAllData(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < allData.size(); i++) {
			System.out.println(allData.get(i).toString());
		}

	}
}
