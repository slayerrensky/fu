package fu.netzsys.ex3.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ugenre {

	// u.genre -- A list of the genres.
	int id;
	String genre;

	public Ugenre(int id, String genre) {
		super();
		this.id = id;
		this.genre = genre;
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
			if (values.length < 2) continue;
			list.add(new Ugenre(Integer.parseInt(values[1]), values[0]));
		}

		return list;
	}
	
	@Override
	public String toString() {
		return genre + " " + id;
	}
}
