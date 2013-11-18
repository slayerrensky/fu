package alphabetic;


public class Alphabetic {
	public static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };
	public static final String ALPHABET_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static int getIndexOfLetter(char letter) {
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] == letter)
				return i;
		}
		return -1;
	}

	public static char getLetterOfIndex(int index) {
		return alphabet[index];
	}

	public static LetterList frequenceAnalyzer(String str) {
		LetterList list = new LetterList();
		list.setText(str);
		list.fillWithAlphabeticLetters();
		
		str = str.toUpperCase();

		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			list.inkrementByLetter(charArray[i]);
		}
		return list;
	}

	public static void printFrequentlyAnalytic(String str) {
		LetterList list = Alphabetic.frequenceAnalyzer(str);
		printFrequentlyAnalytic(list, Integer.MAX_VALUE);
	}
	
	public static void printFrequentlyAnalytic(LetterList list, int n) {
		int sumOfLetters = list.getText().length();

		for (int i = 0; i < n; i++) {
			if (i == list.size()) break;
			LetterListElement letterListElement = list.get(i);
			System.out.printf("%c : %3d : %6.2f %% \n", letterListElement.getLetter(),
					letterListElement.getNumber(), (double) letterListElement.getNumber()
							/ (double) sumOfLetters * 100);
		}
	}
}
