package fu.netzsys.ex3.one;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Udata {
	// u.data -- The full u data set, 100000 ratings by 943 users on 1682 items.
	// Each user has rated at least 20 movies. Users and items are
	// numbered consecutively from 1. The data is randomly
	// ordered. This is a tab separated list of
	// user id | item id | rating | timestamp.
	// The time stamps are unix seconds since 1/1/1970 UTC

	public static final String FILENAME = "u.data";
	private int rating;
	private int unixTimestamp;
	private Uitem item;
	private Uuser user;
	public static ArrayList<Udata> list = new ArrayList<Udata>();

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getUnixTimestamp() {
		return unixTimestamp;
	}

	public void setUnixTimestamp(int unixTimestamp) {
		this.unixTimestamp = unixTimestamp;
	}

	public Uitem getItem() {
		return item;
	}

	public void setItem(Uitem item) {
		this.item = item;
	}

	public Uuser getUser() {
		return user;
	}

	public void setUser(Uuser user) {
		this.user = user;
	}

	public static String getFilename() {
		return FILENAME;
	}

	public Udata(Uuser user, Uitem item, int rating, int unixTimestamp) {
		this.user = user;
		this.item = item;
		this.rating = rating;
		this.unixTimestamp = unixTimestamp;
	}

	public static void fillList(String pathToDir) throws Exception {
		setList(getAllData(pathToDir + FILENAME));
	}

	public static ArrayList<Udata> getAllData(String fullPath) throws Exception {
		ArrayList<Udata> list = new ArrayList<Udata>();

		BufferedReader bufferedReader = Util
				.getBufferedReaderFromFile(fullPath);

		// Read File
		String line = null;
		String[] values = null;
		while ((line = bufferedReader.readLine()) != null) {
			values = Util.getStringListFromStringLine(line, "\\u0009");
			if (values.length < 4)
				continue;
			try {
				Uuser userByID = Uuser.getUserByID(Integer
						.parseInt(values[0]));
				Uitem itemByID = Uitem.getItemByID(Integer
						.parseInt(values[1]));
				Udata udata = new Udata(userByID,itemByID , Integer.parseInt(values[2]),
						Integer.parseInt(values[3]));
				list.add(udata);
				itemByID.getMyRatings().add(udata);
				userByID.getMyRatings().add(udata);
			} catch (Exception e) {
				throw e;
			}

		}
		bufferedReader.close();
		return list;
	}

	@Override
	public String toString() {
		return "" + rating + " " + unixTimestamp + " " + item.toString() + " "
				+ user.toString() + "";
	}

	public ArrayList<Udata> getList() {
		return list;
	}

	public static void setList(ArrayList<Udata> list) {
		Udata.list = list;
	}

	public static void printList() {
		for (int i = 0; i < Udata.list.size(); i++) {
			System.out.println(Udata.list.get(i));
		}
	}

	public static ArrayList<Uitem> getAllItems(ArrayList<Udata> data) {
		ArrayList<Uitem> items = new ArrayList<Uitem>();
		for (int i = 0; i < data.size(); i++) {
			items.add(data.get(i).getItem());
		}
		return items;
	}
	
	public static void add(Uuser user, Uitem item, int rating, int unixTimestamp){
		list.add(new Udata(user,item,rating,unixTimestamp));
	}
}
