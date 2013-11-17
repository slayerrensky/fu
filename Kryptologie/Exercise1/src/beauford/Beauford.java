package beauford;

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
		Beauford.kasiskiTest();
	}

	public static void kasiskiTest() {
		int lenght = 10;
		String subString = "";
		StringList list = new StringList();
		list = new StringList();
		for (int b = 3; b < lenght; b++) {

			for (int i = 0; i < CIPHER.length() - b; i++) {
				subString = CIPHER.substring(i, i + b);
				if (!list.inkrementByString(subString,i)){
					StringListElement stringListElement = new StringListElement(subString, 1);
					stringListElement.getDistance().add(i);
					list.add(stringListElement);
				}
					
			}
		}
		list.removeUnrelevantsObjects();
		list.sortByNumber();
//		list.out();
		list.printDistance();
	}
}
