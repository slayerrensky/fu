package playfair;

import haufigkeit.Bigramme;
import haufigkeit.English;

import java.util.ArrayList;
import java.util.Collections;

import sun.security.acl.WorldGroupImpl;
import fu.commonWords.ColoredFrame;
import fu.commonWords.CommonWord;
import fu.commonWords.WordReader;

public class Main {

	static final String Chiffre = "OR NH RG YO SC DP TF NG AH HS KU BW UH OP OI NH DP BX RS EN FU GN CZ PO EN OR XH MO EN HN TO PI TF ZI RI QH OH OB QH XI KA PM RP HQ HN FB HG IY OB DN PB TL ND OR DV RH AH LF ZY AH XV FS ZK ND HB KF VD CN OR HQ DN UA GB GB GD EX FS RQ EN FB DN HQ IS BD UO CO BO UQ CZ ED TH NX TP RQ PF RP XG IX PR RS EN OR XH OI NG AH BS DY QP GN OR NH NX HN KC DW EN HN CK QH FV QY IU GH GX MS NI SM FO HX YL ZO BX QF DX ZO US WI DR AU XE DX XG ND OR HT VF RO NI QC DR OR BO BO HQ OP ZH KI PR TP GD OZ QD PO CP BK LG BK PD SD HQ ZQ EN IU ZH PL RP CP AH KM CQ NE DV BK BG BK QH MP EN NK ZM RO QU NH TE AH XR QL XV PC OR XE XH VA UN BF NX GB MS EN UQ GR RA CK NX DR VN RQ CB HN OU IP OT NH WX RQ TO PI PI UH OT BH HX RQ EN IT SB BE BO QC RA NX UA SB IB SR BH ME QU MS UQ HC RN DT VN HN XQ GV HT BI IF EK KA RS EN UA DR XD KX RQ UI QU FD DV BK BG BK QH MP EN NK ZM RO QU NH BK BG BK QH MP EN NK ZM RO QU NH";

	// static final String Chiffre =
	// "ME IK QO TX CQ TE ZX CO MW QC TE HN FB IK ME HA KR QC UN GI KM AV";
	// static final String CLEAR =
	// "LA BO UL AY EL AD YW IL LX LE AD TO CI BO LA TE MP LE SO FG OL DX";
	// static final String KEY = "DEATH";

	// static final String KEY = "ABCDE"
	// + "FGHIK"
	// + "LMNOP"
	// + "QRSTU"
	// + "VWXYZ";
	public static void main(String[] args) {

		// char[][] key = PlayFair.generatePlayFairQuadrat(KEY);

		// Print PlayFair Matrix
		// *******
		// System.out.println("5x5 PlayFair Matrix with Key: " + KEY);
		// for (int x = 0; x < key.length; x++) {
		// for (int y = 0; y < key[0].length; y++) {
		// System.out.print(key[x][y]);
		// }
		// System.out.println();
		// }
		// System.out.println();
		// *******

		// String chiffreText = PlayFair.encrypt(key, CLEAR);

		// System.out.println("Encrypt: \n" + CLEAR);
		// System.out.println("With Key: \n" + KEY);
		// System.out.println("Chiffre: \n" + chiffreText);
		// System.out.println("Decrypt: \n" + PlayFair.decrypt(key,
		// chiffreText));
		
		ArrayList<Bigramme> bigramms = PlayFair.getBigramms(Chiffre);
		Collections.sort(bigramms);
		for (Bigramme bigramme : bigramms) {
			System.out.println(bigramme.letters + ": " + bigramme.haufigkeit);
		}

		String chrifeSubst = new String(Chiffre);
		for (int i = 0;i<5;i++){
			chrifeSubst = chrifeSubst.replaceAll(bigramms.get(i).letters, English.getBigrammLetters().get(i).Letters);
		}
		System.out.println(chrifeSubst);
		
		new ColoredFrame(chrifeSubst, bigramms);
	}

	public static void bruteForce() {
		WordReader wr = new WordReader("./5000Common words.txt");

		ArrayList<CommonWord> wordList = wr.readFile();
		wr.addWord(new CommonWord("DEATH", 1, wordList.size() + 1));

		int relevantFound = 0;
		int maximum = 0;
		String mybeSolution = "";
		for (int i = 0; i < wordList.size(); i++) {
			String removeDoubleLetters = PlayFair.removeDoubleLetters(wordList
					.get(i).getWord());

			char[][] generatePlayFairQuadrat = PlayFair
					.generatePlayFairQuadrat(removeDoubleLetters);
			if (generatePlayFairQuadrat == null)
				continue;
			String chiffreText = PlayFair.decrypt(generatePlayFairQuadrat,
					Chiffre);
			chiffreText = chiffreText.replaceAll(" ", "");
			int found = CommonWord.countWords(wordList, chiffreText);

			// OUT
			if (found > 3) {
				if (maximum < found) {
					maximum = found;
					mybeSolution = chiffreText;
				}
				relevantFound++;
				System.out.print(relevantFound + " (" + i + ") try: "
						+ wordList.get(i).getWord());
				if (!removeDoubleLetters.equals(wordList.get(i).getWord()))
					System.out.println(" -> " + removeDoubleLetters);
				else
					System.out.println();
				System.out.println("-> Uebereinstimmungen: " + found);
				System.out.println(chiffreText.replaceAll(" ", ""));
			}
		}
		System.out.println("Maybe the Cleartext with " + maximum + " hits?");
		System.out.println(mybeSolution.replaceAll(" ", ""));
//		new ColoredFrame(mybeSolution, wordList);
	}
}
