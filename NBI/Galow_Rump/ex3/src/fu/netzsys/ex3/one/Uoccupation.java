package fu.netzsys.ex3.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Uoccupation {
	// u.occupation -- A list of the occupations.
	public static final String FILENAME = "u.occupation";
	String occupation;
	public static ArrayList<Uoccupation> list = new ArrayList<Uoccupation>();


	public Uoccupation(String occupation) {
		super();
		this.occupation = occupation;
	}
	
	public static void fillList(String pathToDir) throws IOException{
		setList(getAllData(pathToDir + FILENAME));
	}

	public static ArrayList<Uoccupation> getAllData(String fullPath)
			throws IOException {
		ArrayList<Uoccupation> list = new ArrayList<Uoccupation>();

		BufferedReader bufferedReader = Util
				.getBufferedReaderFromFile(fullPath);

		// Read File
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.equals(""))
				continue;
			list.add(new Uoccupation(line));
		}

		return list;
	}

	@Override
	public String toString() {
		return occupation;
	}

	public ArrayList<Uoccupation> getList() {
		return list;
	}

	public static void setList(ArrayList<Uoccupation> list) {
		Uoccupation.list = list;
	}
	
	public static void printList(){
		for (int i = 0; i < Uoccupation.list.size(); i++) {
			System.out.println(Uoccupation.list.get(i));
		}
	}
}
