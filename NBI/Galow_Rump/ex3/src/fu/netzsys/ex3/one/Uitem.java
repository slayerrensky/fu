package fu.netzsys.ex3.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Uitem {
//	u.item     -- Information about the items (movies); this is a tab separated
//	    list of
//	    movie id | movie title | release date | video release date |
//	    IMDb URL | unknown | Action | Adventure | Animation |
//	    Children's | Comedy | Crime | Documentary | Drama | Fantasy |
//	    Film-Noir | Horror | Musical | Mystery | Romance | Sci-Fi |
//	    Thriller | War | Western |
//	    The last 19 fields are the genres, a 1 indicates the movie
//	    is of that genre, a 0 indicates it is not; movies can be in
//	    several genres at once.
//	    The movie ids are the ones used in the u.data data set.
	
	int id;
	String title;
	String date;
	//nichts
	String link;
	int bits;
	
	public Uitem(int id, String title, String date, String link,
			int bits) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.link = link;
		this.bits = bits;
	}

	public Uitem(int parseInt) {
		//Suche nach gegebenen Objekt
	}

	@Override
		public String toString() {
			return id + "";
		}
	
	public static ArrayList<Uitem> getAllData(String fullPath)
			throws IOException {
		ArrayList<Uitem> list = new ArrayList<Uitem>();

		BufferedReader bufferedReader = Util
				.getBufferedReaderFromFile(fullPath);

		// Read File
		String line = null;
		String[] values = null;
		while ((line = bufferedReader.readLine()) != null) {
			values = Util.getStringListFromStringLine(line, "\\u007C");
			if (values.length < 23) continue;
//			Integer.parseInt(values[5]);
			list.add(new Uitem(Integer.parseInt(values[0]), values[1], values[2], values[4], Integer.parseInt(values[5])));
		}

		return list;
	}
}
