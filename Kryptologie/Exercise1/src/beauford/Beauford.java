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
		
		
		// http://www.personal.psu.edu/users/m/r/mrk5094/Kasiski.html
		
		int kGV = 5;
		char[][] kasiskiList = new char[kGV][(int)(CIPHER.length()/kGV)+1]; 
 		
		
		int i = 0;
		for(char c : CIPHER.toCharArray())
		{
			kasiskiList[i%kGV][(int)(i/kGV)] = c;
			i++;
		}
		
		System.out.println(kasiskiList.length);
		
		ArrayList<String> kasiski = new ArrayList<String>(); 
		for(i=0; i < kGV; i++)
		{
			String s = new String();
			for (int j = 0; j < (int) (kasiskiList[0].length);j++)
			{
				s += String.valueOf(kasiskiList[i][j]);
			}
			kasiski.add(s);
		}
		
		for(String s : kasiski)
		{
			System.out.println(s);
			LetterList l = Alphabetic.frequenceAnalyzer(s);
			l.sortByNumber();
			for (i = 0; i <= 5; i++)
			{
				System.out.print(l.get(i).getLetter() + "->" + l.get(i).getNumber() + " ");
			}
			System.out.println();
		}
	}
}
