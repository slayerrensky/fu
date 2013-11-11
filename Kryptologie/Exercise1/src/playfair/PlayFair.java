package playfair;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;

import sun.org.mozilla.javascript.internal.ast.WithStatement;

public class PlayFair {

	static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };
	static final String ALPHABETSTRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public PlayFair() {
	}

	/**
	 * @param key
	 *            in From von "Schluessel" (Beispiel)
	 * @return die 5x5 Matrix
	 */
	public static char[][] generatePlayFairQuadrat(String key) {
		if (key.length() > 25)
			return null;

		String alphabetToReduce = new String(ALPHABETSTRING).replaceAll("J", "");
		char[][] pfQuadrat = new char[5][5];
		char[] charArray = key.toUpperCase().toCharArray();
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				if (key.length() > (x * 5 + y)) {
					pfQuadrat[x][y] = charArray[(x * 5 + y)];
					alphabetToReduce = alphabetToReduce.replace(
							String.valueOf(charArray[(x * 5 + y)]), "");
				} else {
					char c = alphabetToReduce.toCharArray()[0];
					pfQuadrat[x][y] = c;
					alphabetToReduce = alphabetToReduce.replace(String.valueOf(c), "");
				}
			}
		}
		if (alphabetToReduce.length() != 0) return null;
		return pfQuadrat;
	}

	/**
	 * @param key
	 *            key in Form einer 5x5 Matrix
	 * @param clearText
	 *            in Form von zwei Grossbuchstaben und Leerzeichen getrennt
	 *            "AH CS YC ..." (Beispiel)
	 * @return Chiffre in Form von zwei Grossbuchstaben und Leerzeichen getrennt
	 *         "VG VT SH ..." (Beispiel)
	 */
	public static String encrypt(char[][] key, String clearText) {
		String chiffre = "";
		String[] split = clearText.split(" ");
		
		for (int i = 0; i < split.length; i++) {

			chiffre += getCryptLetters(key, split[i]) + " ";
		}
		
		
		return chiffre;
	}
	
	
	/**  y   mk
	 *  0|x | k x x
	 *  1|x x x x x
	 *  2|x x x x x
	 *  3|x m _ x x
	 *  4|x x x x x
	 *   ---------- x width
	 *    0 1 2 3 4
	 *   x = x  Jewels den Rechten Platz also x + 1 % 5
	 *   y = y  Jewels den unteren platz also y + 1 % 5 
	 *   x != y Kreuzung
	 *   	m (1,3)    -> _ bei den h�heren also (3,2) 
	 *      k (2,0)    -> | bei den niedrigen also (1,0)  
	 *   		
	 *   2 buchstande erst y dann x  
	 */
	protected static String getCryptLetters(char[][] key, String s) {
		char[] c = s.toCharArray();
		
		Dimension first = new Dimension(); 
		Dimension secound = new Dimension(); 
		
		// find letter
		for (int x = 0; x < key.length; x++) {
			for (int y = 0; y < key[0].length; y++) {
				if (c[0] == key[x][y]) {
					first.height = y;
					first.width = x;
				}
				if (c[1] == key[x][y]) {
					secound.height = y;
					secound.width = x;
				}
			}
		}
		
		char[] out = new char[2];
		
		if(first.height == secound.height){  // y = y
			out[0] =  key[first.height][(first.width + 1 ) % 5];
			out[1] =  key[secound.height][(secound.width + 1 ) % 5];
		} else if ( first.width == secound.width){ // x = x 
			out[0] =  key[(first.height+ 1 ) % 5][first.width];
			out[1] =  key[(secound.height+ 1 ) % 5][secound.width];
		} else { //x != y Kreuzung
			// Zeile beleibt gleich (y) height
			// spalte des anderen 
			
			out[0] = key[first.width][secound.height]; //key[x][y] = key [width][height]
			out[1] = key[secound.width][first.height];
		}

		return String.valueOf(out[0]) + String.valueOf(out[1]); 
	}
}
