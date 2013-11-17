package alphabetic;

import java.util.Arrays;


public class Alphabetic {
	public static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };
	public static final String ALPHABET_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static int getIndexOfLetter(char letter) {
		return Arrays.asList(alphabet).indexOf(letter);
	}

	public static char getLetterOfIndex(int index) {
		return alphabet[index];
	}
}
