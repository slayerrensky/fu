package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> numbers = getNumbersFromFile("main/numbers.txt");

		if (numbers == null)
			return;
		ArrayList<String> spellingNumbersFromIntegerList = getSpellingNumbersFromIntegerList(numbers);

		for (int i = 0; i < spellingNumbersFromIntegerList.size(); i++) {
			System.out.println(spellingNumbersFromIntegerList.get(i));
		}
	}

	private static ArrayList<Integer> getNumbersFromFile(String file) {

		ArrayList<Integer> numbers = new ArrayList<Integer>();
		try {
			File ff = new File(file);
			FileReader fr = new FileReader(ff);
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			while ((line = br.readLine()) != null) {
				try {
					Integer integer = Integer.parseInt(line);
					if (integer == null)
						continue;
					if (integer < 0 || integer > 1000000)
						continue;
					numbers.add(integer);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
			return numbers;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static ArrayList<String> getSpellingNumbersFromIntegerList(
			ArrayList<Integer> number) {

		Collections.sort(number);
		ArrayList<String> myNumbersAsString = new ArrayList<String>();
		for (int i = 0; i < number.size(); i++) {
			myNumbersAsString.add(getSpellingNumberFromInteger(number.get(i)));
		}
		return myNumbersAsString;
	}

	public static String getSpellingNumberFromInteger(Integer i) {
		String string = i.toString();
		Integer i3 = i;
		int size = string.length();

		String number = "";
		if (i == 0)
			return "" + i + ": " + Words.zero;
		// Tausender
		if (size > 3) {
			i3 = Integer.parseInt(string.substring(0, size-3));
			number = Words.get3Number(i3) + " " + Words.thousand + " ";

			i3 = Integer.parseInt(string.substring(size-3));
			number += Words.get3Number(i3);
		} else {
			i3 = Integer.parseInt(string);
			number += Words.get3Number(i3);
		}
		
		number = number.replaceAll("\\u0020\\u0020", " ").replaceAll("\\u0020$", "");
		return "" + i + ": " + number;
	}
}
