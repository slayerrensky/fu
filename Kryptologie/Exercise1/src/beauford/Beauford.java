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
//		Beauford.kasiskiTest();
		a();
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
	
	public static char[][] permutateForAnalytic(int kGV){
		char[][] kasiskiList = new char[kGV][(int)(CIPHER.length()/kGV)+1]; 
 		
		
		int i = 0;
		for(char c : CIPHER.toCharArray())
		{
			kasiskiList[i%kGV][(int)(i/kGV)] = c;
			i++;
		}
		return kasiskiList;
	}
	
	public static void a(){
		// http://www.personal.psu.edu/users/m/r/mrk5094/Kasiski.html
		int kGV = 5;
		char[][] kasiskiList = permutateForAnalytic(kGV);
		
		// Listen zusammen basteln 2)
		ArrayList<String> kasiski = new ArrayList<String>(); 
		for(int i=0; i < kGV; i++)
		{
			String s = new String();
			for (int j = 0; j < (int) (kasiskiList[0].length);j++)
			{
				s += String.valueOf(kasiskiList[i][j]);
			}
			
			
			
			// Austauschen der meist bentzten buchstaben durch e un t
			LetterList l = Alphabetic.frequenceAnalyzer(s);
			l.sortByNumber();
			s = s.toLowerCase();
			for (int j=0;j<5;j++)
			{
				System.out.print(l.get(j).getLetter() + "->" + l.get(j).getNumber() + " ");
				
			}
			switch (i) {
			case 1: // Zeile 2 koennte auch ein h als meist benutzen Buchstaben haben
				s = s.replace((char)(l.get(0).getLetter() + 32), 'H');
				s = s.replace((char)(l.get(1).getLetter() + 32), 'T');
				//s = s.replace((char)(l.get(2).getLetter() + 32), 'A');
				break;
//			case 3: 
//				s = s.replace('m', 'R'); // Zeile 4 angenommen das m zu R getauscht wird
//				s = s.replace((char)(l.get(0).getLetter() + 32), 'E');
//				s = s.replace((char)(l.get(1).getLetter() + 32), 'T');
//				s = s.replace((char)(l.get(2).getLetter() + 32), 'A');
//				break;
			default:
				s = s.replace((char)(l.get(0).getLetter() + 32), 'E');
				s = s.replace((char)(l.get(1).getLetter() + 32), 'T');
				s = s.replace((char)(l.get(2).getLetter() + 32), 'A');
				break;
			}
			
			System.out.println(" " + s);

			
			kasiski.add(s);
		}
		
		// zurueck formen
		
		String out = new String();
		for (int i = 0; i< (int)(CIPHER.length()/kGV+1); i++)
		{
			for (int j=0;j<kGV;j++){
				out += kasiski.get(j).substring(i,i+1);
			}
		}
		
		System.out.println();
		
		int offset = 60;
		for (int i = 0; i< out.length(); i+=offset){
			if (offset < out.length()-i)
				System.out.println(out.substring(i,i+offset));
			else 
				System.out.println(out.substring(i,out.length()));
		}
		
		// vielleicht koennte man mit den most common words etwas entwickeln was diese mit den text vergleicht.
	}
}
