package beauford;

import java.util.ArrayList;

import alphabetic.Alphabetic;
import alphabetic.LetterList;

public class Beauford {
	static String CIPHER = "MKKBPNRBPVNNKGBMKKMFUOKFBVTOFQZDDPPSZKOHNYHOBFELUEURGFGRKORLMRVKFSFKOANYHOPHNUFIBZWKGMKKLFOJPEGOJCAFTNHSIAVOUZQYHOIXFKAAREKWTUGVLTMZZEARMNHTDHGAGRYOAAFJBECNKOPFJQUZTDRTOCSKAHPJKKBPNDCOFSNUAPCYAQIXFNSGCPXSXUYABPNYJBFTKGASBZKZAXEINPFAWKBBNVLPDRDHZNDBLLNZAYIMKKPLNRZDPFAGFNURWZFAKGGTSOANMXZXSRBZVSCKRVKFSZDKHRKQOTOZOMFMNBZMRXWSGCTKSCN";

	public static void calc() {
		LetterList frequenceAnalyzer = Alphabetic.frequenceAnalyzer(CIPHER);
		frequenceAnalyzer.sortByNumber();
		Alphabetic.printFrequentlyAnalytic(frequenceAnalyzer);
	}

	public static void main(String[] args) {
		// Beauford.kasiskiTest();
		analyticAtack();
	}

	public static void kasiskiTest() {
		int lenght = 10;
		String subString = "";
		StringList list = new StringList();
		list = new StringList();
		for (int b = 3; b < lenght; b++) {

			for (int i = 0; i < CIPHER.length() - b; i++) {
				subString = CIPHER.substring(i, i + b);
				if (!list.inkrementByString(subString, i)) {
					StringListElement stringListElement = new StringListElement(
							subString, 1);
					stringListElement.getDistance().add(i);
					list.add(stringListElement);
				}

			}
		}
		list.removeUnrelevantsObjects();
		list.sortByNumber();
		// list.out();
		list.printDistance();

	}

	public static char[][] permutateForAnalytic(int kGV) {
		char[][] kasiskiList = new char[kGV][0];
		int ueberschuss = CIPHER.length() % kGV;
		for (int i = 0; i < kGV; i++) {
			int lenght = CIPHER.length() / kGV;
			if (i < ueberschuss)
				lenght++;
			kasiskiList[i] = new char[lenght];
		}

		int i = 0;
		for (char c : CIPHER.toCharArray()) {
			kasiskiList[i % kGV][(int) (i / kGV)] = c;
			i++;
		}
		return kasiskiList;
	}

	public static void substituateLetter(ArrayList<String> list) {

		LetterList l = Alphabetic.frequenceAnalyzer(list.get(0));
		String s = "";
		for (int i = 0; i < list.size(); i++) {
			s = list.get(i);
			switch (i) {
			case 1: // Zeile 2 koennte auch ein h als meist benutzen Buchstaben
					// haben
				s = s.replace((char) (l.get(0).getLetter() + 32), 'H');
				s = s.replace((char) (l.get(1).getLetter() + 32), 'T');
				// s = s.replace((char)(l.get(2).getLetter() + 32), 'A');
				break;
			case 3:
				// s = s.replace('m', 'R'); // Zeile 4 angenommen das m zu R
				// s = s.replace((char) (l.get(0).getLetter() + 32), 'E');
				// s = s.replace((char) (l.get(1).getLetter() + 32), 'T');
				// s = s.replace((char) (l.get(2).getLetter() + 32), 'A');
				break;
			default:
				s = s.replace((char) (l.get(0).getLetter() + 32), 'E');
				s = s.replace((char) (l.get(1).getLetter() + 32), 'T');
				s = s.replace((char) (l.get(2).getLetter() + 32), 'A');
				break;
			}
		}
	}

	public static void analyticAtack() {
		// http://www.personal.psu.edu/users/m/r/mrk5094/Kasiski.html

		// Assumption: Aus Beauford.kasiskiTest() koennen wir die Annahme
		// treffen, dass die Schluessellaenge 5 betraegt
		int kGV = 5;

		// Fuer die Frequenzanalyse muessen wir Buchstaben mit dem gleichen
		// Zeichen des Schluessels gemeinsa betrachten -> Permutation
		
		//Permutation
		ArrayList<String> kasiskiList = convertToFrequentAnalyticList(kGV);
		//Buchstaben ersetzen
		substituateLetter(kasiskiList);
		//Permutation zurueck
		String out = convertToNormalList(kasiskiList);

		// Print
		String outText = "";
		int step = 10;
		for (int i = 0; i < out.length(); i += step) {
			if (i != 0 && i % 80 == 0)
				outText += "\n";
			else if (i != 0)
				outText += ",";
			if (out.length() < i + step)
				outText += out.substring(i, out.length());
			else
				outText += out.substring(i, i + step);

		}
		System.out.println(outText);
	}

	private static String convertToNormalList(
			ArrayList<String> kasiskiList) {
		String out = new String();
		for (int i = 0; i < (int) (CIPHER.length() / (kasiskiList.size())) + 1; i++) {
			for (int j = 0; j < kasiskiList.size(); j++) {
				if (kasiskiList.size() > j
						&& kasiskiList.get(j).length() >= i + 1)
					out += kasiskiList.get(j).substring(i, i + 1);
			}
		}
		return out;
	}

	private static ArrayList<String> convertToFrequentAnalyticList(int kGV) {
		char[][] kasiskiArray = new char[kGV][0];
		int ueberschuss = CIPHER.length() % kGV;
		for (int i = 0; i < kGV; i++) {
			int lenght = CIPHER.length() / kGV;
			if (i < ueberschuss)
				lenght++;
			kasiskiArray[i] = new char[lenght];
		}

		char[] charArray = CIPHER.toCharArray();
		for (int i = 0;i<charArray.length;i++){
			kasiskiArray[i % kGV][(int) (i / kGV)] = charArray[i];
		}
		
		ArrayList<String> kasiskiList = new ArrayList<String>();
		for (int i = 0; i < kasiskiArray.length; i++) {
			String s = new String();
			for (int j = 0; j < kasiskiArray[i].length; j++) {
				s += String.valueOf(kasiskiArray[i][j]);
			}
			kasiskiList.add(s);
		}
		return kasiskiList;
	}
}
