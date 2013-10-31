package fu.netzsys.ex3.one;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Udata {
	// u.data -- The full u data set, 100000 ratings by 943 users on 1682 items.
	// Each user has rated at least 20 movies. Users and items are
	// numbered consecutively from 1. The data is randomly
	// ordered. This is a tab separated list of
	// user id | item id | rating | timestamp.
	// The time stamps are unix seconds since 1/1/1970 UTC

	public int rating;
	public int unixTimestamp;
	public Uitem item;
	public Uuser user;

	public Udata(Uuser user, Uitem item, int rating, int unixTimestamp) {
		this.user = user;
		this.item = item;
		this.rating = rating;
		this.unixTimestamp = unixTimestamp;
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
				list.add(new Udata(new Uuser(Integer.parseInt(values[0])),
						new Uitem(Integer.parseInt(values[1])), Integer
								.parseInt(values[2]), Integer
								.parseInt(values[3])));
			} catch (Exception e) {
				throw e;
			}

		}

		return list;
	}

	@Override
	public String toString() {
		return "" + rating + " " + unixTimestamp + " " + item.toString() + " "
				+ user.toString() + "";
	}
}
