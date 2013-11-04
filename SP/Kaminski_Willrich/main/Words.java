package main;

public class Words {
	/*
	 * zero, one, two, three, four, five, six, seven, eight, nine, ten, eleven,
	 * twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen,
	 * nineteen, twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety,
	 * hundred, thousand.
	 */

	public static final String zero = "zero";
	public static final String one = "one";
	public static final String two = "two";
	public static final String three = "three";
	public static final String four = "four";
	public static final String five = "five";
	public static final String six = "six";
	public static final String seven = "seven";
	public static final String eight = "eight";
	public static final String nine = "nine";
	public static final String ten = "ten";
	public static final String eleven = "eleven";
	public static final String twelve = "twelve";
	public static final String thirteen = "thirteen";
	public static final String fourteen = "fourteen";
	public static final String fifteen = "fifteen";
	public static final String sixteen = "sixteen";
	public static final String seventeen = "seventeen";
	public static final String eighteen = "eighteen";
	public static final String nineteen = "nineteen";
	public static final String twenty = "twenty";
	public static final String thirty = "thirty";
	public static final String forty = "forty";
	public static final String fifty = "fifty";
	public static final String sixty = "sixty";
	public static final String seventy = "seventy";
	public static final String eighty = "eighty";
	public static final String ninety = "ninety";
	public static final String hundred = "hundred";
	public static final String thousand = "thousand";

	public static String get3Number(Integer number) {
		if (number >= 1000)
			return null;
		String stringNumber = "";
		int pitch2 = number % 1000;

		int firstNumber = ((int) (pitch2 / 100));
		int secondNumber = ((int) ((number % 100) / 10));
		int thirdNumber = pitch2 % 10;

		// Unter zwanzig
		if (secondNumber <= 1) {
			String stringFromNumber = getStringFromNumber((number % 100));
			stringNumber = stringFromNumber;

			// über zwanzig
		} else {
			int tenPitch = secondNumber * 10;
			String stringFromNumber = getStringFromNumber(tenPitch);
			stringNumber = stringFromNumber + " "
					+ getStringFromNumber(thirdNumber);
		}

		// hunderter
		if (firstNumber != 0) {
			String stringFromNumber = getStringFromNumber(firstNumber);
			stringNumber = stringFromNumber + " " + Words.hundred + " "
					+ stringNumber;
		}
		return stringNumber;
	}

	private static String getStringFromNumber(Integer number) {
		switch (number) {
		case 0:
			return "";
		case 1:
			return Words.one;
		case 2:
			return Words.two;
		case 3:
			return Words.three;
		case 4:
			return Words.four;
		case 5:
			return Words.five;
		case 6:
			return Words.six;
		case 7:
			return Words.seven;
		case 8:
			return Words.eight;
		case 9:
			return Words.nine;
		case 10:
			return Words.ten;
		case 11:
			return Words.eleven;
		case 12:
			return Words.twelve;
		case 13:
			return Words.thirteen;
		case 14:
			return Words.fourteen;
		case 15:
			return Words.fifteen;
		case 16:
			return Words.sixteen;
		case 17:
			return Words.seventeen;
		case 18:
			return Words.eighteen;
		case 19:
			return Words.nineteen;
		case 20:
			return Words.twenty;
		case 30:
			return Words.thirty;
		case 40:
			return Words.forty;
		case 50:
			return Words.fifty;
		case 60:
			return Words.sixty;
		case 70:
			return Words.seventy;
		case 80:
			return Words.eighty;
		case 90:
			return Words.ninety;
		case 100:
			return Words.hundred;
		case 1000:
			return Words.thousand;
		default:
			return null;
		}
	}
}
