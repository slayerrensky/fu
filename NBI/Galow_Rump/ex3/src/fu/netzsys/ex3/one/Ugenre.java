package fu.netzsys.ex3.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ugenre {

	// u.genre -- A list of the genres.
	public static final String FILENAME = "u.genre";
	int id;
	String genre;
	public static ArrayList<Ugenre> list = new ArrayList<Ugenre>();

	public Ugenre(int id, String genre) {
		super();
		this.id = id;
		this.genre = genre;
	}

	public static void fillList(String pathToDir) throws IOException {
		setList(getAllData(pathToDir + FILENAME));
	}

	public static ArrayList<Ugenre> getAllData(String fullPath)
			throws IOException {
		ArrayList<Ugenre> list = new ArrayList<Ugenre>();

		BufferedReader bufferedReader = Util
				.getBufferedReaderFromFile(fullPath);

		// Read File
		String line = null;
		String[] values = null;
		while ((line = bufferedReader.readLine()) != null) {
			values = Util.getStringListFromStringLine(line, "\\u007C");
			if (values.length < 2)
				continue;
			list.add(new Ugenre(Integer.parseInt(values[1]), values[0]));
		}

		return list;
	}

	@Override
	public String toString() {
		return genre + " " + id;
	}

	public ArrayList<Ugenre> getList() {
		return list;
	}

	public static void setList(ArrayList<Ugenre> list) {
		Ugenre.list = list;
	}

	public static void printList() {
		for (int i = 0; i < Ugenre.list.size(); i++) {
			System.out.println(Ugenre.list.get(i));
		}
	}
}
