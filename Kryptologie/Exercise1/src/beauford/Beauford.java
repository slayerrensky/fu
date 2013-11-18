package beauford;

import haufigkeit.English;

import java.util.ArrayList;

import javax.crypto.Cipher;

import alphabetic.Alphabetic;
import alphabetic.LetterList;

public class Beauford {
	static String CIPHER = "MKKBPNRBPVNNKGBMKKMFUOKFBVTOFQZDDPPSZKOHNYHOBFELUEURGFGRKORLMRVKFSFKOANYHOPHNUFIBZWKGMKKLFOJPEGOJCAFTNHSIAVOUZQYHOIXFKAAREKWTUGVLTMZZEARMNHTDHGAGRYOAAFJBECNKOPFJQUZTDRTOCSKAHPJKKBPNDCOFSNUAPCYAQIXFNSGCPXSXUYABPNYJBFTKGASBZKZAXEINPFAWKBBNVLPDRDHZNDBLLNZAYIMKKPLNRZDPFAGFNURWZFAKGGTSOANMXZXSRBZVSCKRVKFSZDKHRKQOTOZOMFMNBZMRXWSGCTKSCN";

	public static void calc() {
		LetterList frequenceAnalyzer = Alphabetic.frequenceAnalyzer(CIPHER);
		frequenceAnalyzer.sortByNumber();
		Alphabetic
				.printFrequentlyAnalytic(frequenceAnalyzer, Integer.MAX_VALUE);
	}

	public static void main(String[] args) {
		// Beauford.kasiskiTest();
		analyticAtack();
		
//		String key = "A";
//		String distanceWord = getDistanceWord("MKK", "THE", "-");
//		System.out.println(distanceWord);
//		decode(key);
	}
	
	private static String getDistanceWord(String a, String b, String op){
		if (a.length() != b.length()) return null;
		char[] aArray = a.toUpperCase().toCharArray();
		char[] bArray = b.toUpperCase().toCharArray();
		
		String s = "";
		for (int i = 0;i<aArray.length;i++){
			int indexOfLetter_a = Alphabetic.getIndexOfLetter(aArray[i]);
			int indexOfLetter_b = Alphabetic.getIndexOfLetter(bArray[i]);
			int goalLetter = 0;
			if (op.endsWith("-")){
				if (indexOfLetter_a - indexOfLetter_b < 0) indexOfLetter_a += Alphabetic.ALPHABET_STRING.length();
				goalLetter = indexOfLetter_a - indexOfLetter_b;
			}else{
				goalLetter = indexOfLetter_a + indexOfLetter_b;
			}
			goalLetter = goalLetter % Alphabetic.ALPHABET_STRING.length();
			s+=Alphabetic.getLetterOfIndex(goalLetter);
		}
		return s;
	}

	private static void decode(String key) {
		char[] keyArray = key.toCharArray();
		char[] cipherArray = CIPHER.toCharArray();
		for (int i = 0;i<cipherArray.length;i++){
			System.out.print(getDistanceWord(""+cipherArray[i], ""+keyArray[i%key.length()], "-"));
		}
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

	public static ArrayList<String> substituateLetter(ArrayList<String> list,
			ArrayList<LetterList> letterList, boolean print, int defaultDeep) {

		ArrayList<String> substituatetList = new ArrayList<String>();
		String s = "";
		for (int i = 0; i < list.size(); i++) {
			LetterList l = letterList.get(i);

			s = list.get(i);
			ArrayList<Character> commonLetters = English.getCommonLetters();

			switch (i) {
			case 0:
				// s = s.replace((char) (l.get(0).getLetter()), '...');
				break;
			case 1:
				l.removeLetter('K');
				s = s.replaceAll("K", "!" + (int) 'H' + "!");
				break;
			case 2:
				l.removeLetter('B');
				s = s.replaceAll("B", "!" + (int) 'E' + "!");
				break;
			case 3:
				// s = s.replace((char) (l.get(0).getLetter()), '...');
				break;
			case 4:
				// s = s.replace((char) (l.get(0).getLetter()), '...');
				break;
			default:
				break;
			}

			// Automatische Substitution
			boolean automaticSubstituation = true;
			if (defaultDeep > l.size())
				defaultDeep = l.size();
			if (automaticSubstituation) {
				for (int x = 0; x < defaultDeep; x++) {
					s = s.replaceAll("" + (l.get(x).getLetter()), "!"
							+ (int) commonLetters.get(x) + "!");
				}
				for (int x = 0; x < defaultDeep; x++) {
					s = s.replaceAll("!" + (int) commonLetters.get(x) + "!", ""
							+ (commonLetters.get(x)));
				}
			} else {
				for (int x = 0; x < defaultDeep; x++) {
					s = s.replaceAll("!" + (int) commonLetters.get(x) + "!", ""
							+ (commonLetters.get(x)));
				}
			}

			substituatetList.add(s);
		}
		return substituatetList;
	}

	public static void analyticAtack() {
		// http://www.personal.psu.edu/users/m/r/mrk5094/Kasiski.html

		int deepNumberForSubstutuate = Integer.MAX_VALUE;
		// Assumption: Aus Beauford.kasiskiTest() koennen wir die Annahme
		// treffen, dass die Schluessellaenge 5 betraegt
		int kGV = 5;

		// Fuer die Frequenzanalyse muessen wir Buchstaben mit dem gleichen
		// Zeichen des Schluessels gemeinsa betrachten -> Permutation

		// Permutation
		ArrayList<String> kasiskiList = convertToFrequentAnalyticList(kGV);
		// Frequenzanalyse
		ArrayList<LetterList> frequentList = frequentAnalytic(kasiskiList, true);
		// Buchstaben ersetzen
		ArrayList<String> substituateLetter = substituateLetter(kasiskiList,
				frequentList, true, deepNumberForSubstutuate);
		// Permutation zurueck
		String out = convertToNormalList(substituateLetter);

		// Ausgabe der Haufigkeiten
		for (int i = 0; i < frequentList.size(); i++) {
			System.out
					.println("Haufigkeiten der " + (i + 1) + "ten Buchstaben");
			Alphabetic.printFrequentlyAnalytic(frequentList.get(i), 5);
			System.out.println();
		}
		// Vorher - Print
		analyticPrint(CIPHER);
		System.out.println();

		// Nachher - Print
		System.out.println();
		analyticPrint(out);
	}

	private static ArrayList<LetterList> frequentAnalytic(
			ArrayList<String> kasiskiList, boolean sort) {
		ArrayList<LetterList> kasiskiListList = new ArrayList<LetterList>();
		for (int i = 0; i < kasiskiList.size(); i++) {
			LetterList l = Alphabetic.frequenceAnalyzer(kasiskiList.get(i));
			if (sort)
				l.sortByNumber();
			kasiskiListList.add(l);
		}
		return kasiskiListList;
	}

	private static void analyticPrint(String out) {
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

	private static String convertToNormalList(ArrayList<String> kasiskiList) {
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
		for (int i = 0; i < charArray.length; i++) {
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
