package fu.netzsys.ex3.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Uuser {
	// u.user -- Demographic information about the users; this is a tab
	// separated list of
	// user id | age | gender | occupation | zip code
	// The user ids are the ones used in the u.data data set.

	public static final String FILENAME = "u.user";
	int id = -1;
	private int age = -1;
	private String male = "";
	private String zipCode = "";
	private Uoccupation occupation = null;
	public static ArrayList<Uuser> list = new ArrayList<Uuser>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Uoccupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Uoccupation occupation) {
		this.occupation = occupation;
	}

	public static String getFilename() {
		return FILENAME;
	}

	public Uuser(int id) {
		super();
		this.id = id;
	}

	// 2|53|F|other|94043

	public Uuser(int id, int age, String male, Uoccupation occupation,
			String zipCode) {
		this.id = id;
		this.age = age;
		this.male = male;
		this.occupation = occupation;
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return id + "" + " " + age + " " + male + " " + occupation + " "
				+ zipCode;
	}

	public static void fillList(String pathToDir) throws IOException {
		setList(getAllData(pathToDir + FILENAME));
	}

	public static ArrayList<Uuser> getAllData(String fullPath)
			throws IOException {
		ArrayList<Uuser> list = new ArrayList<Uuser>();

		BufferedReader bufferedReader = Util
				.getBufferedReaderFromFile(fullPath);

		// Read File
		String line = null;
		String[] values = null;
		while ((line = bufferedReader.readLine()) != null) {
			values = Util.getStringListFromStringLine(line, "\\u007C");
			if (values.length < 5)
				continue;
			list.add(new Uuser(Integer.parseInt(values[0]), Integer
					.parseInt(values[1]), values[2], Uoccupation
					.getOccupateFromValue(values[3]), values[4]));
		}

		bufferedReader.close();
		return list;
	}

	public ArrayList<Uuser> getList() {
		return list;
	}

	public static void setList(ArrayList<Uuser> list) {
		Uuser.list = list;
	}

	public static void printList() {
		for (int i = 0; i < Uuser.list.size(); i++) {
			System.out.println(Uuser.list.get(i));
		}
	}

	public static Uuser getUserByID(int parseInt) {
		for (int i = 0; i < Uuser.list.size(); i++) {
			if (parseInt == Uuser.list.get(i).getId())
				return Uuser.list.get(i);
		}
		return null;
	}
}
