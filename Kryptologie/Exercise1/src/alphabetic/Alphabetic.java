package alphabetic;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

	public static int[] frequenceAnalyzer(String str) {
		str = str.toUpperCase();
		int[] numbers = new int[alphabet.length];

		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			int index = getIndexOfLetter(charArray[i]);
			numbers[index]++;
		}
		return numbers;
	}

	public static void printFrequentlyAnalytic(String str) {
		int[] frequenceAnalyzer = Alphabetic.frequenceAnalyzer(str);
		int sumOfLetters = str.length();

		for (int i = 0; i < frequenceAnalyzer.length; i++) {
			System.out.printf("%c : %3d : %6.2f %% \n", alphabet[i],
					frequenceAnalyzer[i], (double) frequenceAnalyzer[i]
							/ (double) sumOfLetters * 100);
		}
	}
}
