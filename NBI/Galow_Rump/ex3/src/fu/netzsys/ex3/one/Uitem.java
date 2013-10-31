package fu.netzsys.ex3.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Uitem {
	// u.item -- Information about the items (movies); this is a tab separated
	// list of
	// movie id | movie title | release date | video release date |
	// IMDb URL | unknown | Action | Adventure | Animation |
	// Children's | Comedy | Crime | Documentary | Drama | Fantasy |
	// Film-Noir | Horror | Musical | Mystery | Romance | Sci-Fi |
	// Thriller | War | Western |
	// The last 19 fields are the genres, a 1 indicates the movie
	// is of that genre, a 0 indicates it is not; movies can be in
	// several genres at once.
	// The movie ids are the ones used in the u.data data set.

	public static final String FILENAME = "u.item";
	int id;
	String title;
	String date;
	// nichts
	String link;
	String bits;
	public static ArrayList<Uitem> list = new ArrayList<Uitem>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBits() {
		return bits;
	}

	public void setBits(String bits) {
		this.bits = bits;
	}

	public static String getFilename() {
		return FILENAME;
	}

	public Uitem(int id, String title, String date, String link, String bits) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.link = link;
		this.bits = bits;
	}

	@Override
	public String toString() {
		return id + " " + title + " " + date + " " + link + " " + bits;
	}

	public static void fillList(String pathToDir) throws IOException {
		setList(getAllData(pathToDir + FILENAME));
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
			if (values.length < 23)
				continue;
			String bitfield = values[5] + values[6] + values[7] + values[8]
					+ values[9] + values[10] + values[11] + values[12]
					+ values[13] + values[14] + values[15] + values[16]
					+ values[17] + values[18] + values[19] + values[20]
					+ values[21] + values[22] + values[23];

			list.add(new Uitem(Integer.parseInt(values[0]), values[1],
					values[2], values[4], bitfield));
		}

		return list;
	}

	public ArrayList<Uitem> getList() {
		return list;
	}

	public static void setList(ArrayList<Uitem> list) {
		Uitem.list = list;
	}

	public static void printList() {
		for (int i = 0; i < Uitem.list.size(); i++) {
			System.out.println(Uitem.list.get(i));
		}
	}

	public static Uitem getItemByID(int parseInt) {
		for (int i = 0; i < Uitem.list.size(); i++) {
			if (parseInt == Uitem.list.get(i).getId())
				return Uitem.list.get(i);
		}
		return null;
	}
}
