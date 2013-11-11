package playfair;


public class PlayFair {

	public PlayFair() {
	}
	
	
	/**
	 * @param key in From von "Schluessel" (Beispiel)
	 * @return die 5x5 Matrix
	 */
	public static String[][] generatePlayFairQuadrat(String key){
		return null;
	}

	/**
	 * @param key key in Form einer 5x5 Matrix
	 * @param clearText in Form von zwei Grossbuchstaben und Leerzeichen getrennt 	"AH CS YC ..."  (Beispiel)
	 * @return Chiffre in Form von zwei Grossbuchstaben und Leerzeichen getrennt 	"VG VT SH ..."  (Beispiel)
	 */
	public static String encrypt(String[][] key, String clearText) {
		String chiffre = "";
		String[] split = clearText.split(" ");
		
		for (int i = 0;i<split.length;i++){
			//Verschluessel split[i]..
		}
		return chiffre;
	}
}
