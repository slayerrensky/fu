package playfair;

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
			// Verschluessel split[i]..
		}
		return chiffre;
	}
}
