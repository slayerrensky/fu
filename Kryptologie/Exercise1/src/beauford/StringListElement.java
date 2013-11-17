package beauford;

import java.util.ArrayList;

public class StringListElement {
	String myString = "";
	int number = 0;
	ArrayList<Integer> distance = new ArrayList<Integer>();

	public StringListElement() {
	}

	public StringListElement(String string, int number) {
		this.myString = string;
		this.number = number;
	}

	public StringListElement(String string) {
		this(string, 0);
	}

	public StringListElement(int number) {
		this("", number);
	}

	public String getString() {
		return myString;
	}

	public void setLetter(String string) {
		this.myString = string;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Integer> getDistance() {
		return distance;
	}
	
}
