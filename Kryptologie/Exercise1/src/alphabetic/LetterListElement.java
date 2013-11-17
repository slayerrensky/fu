package alphabetic;

import java.util.ArrayList;

import beauford.StringListElement;

public class LetterListElement {
	char letter = 0;
	int number = 0;

	public LetterListElement() {
	}
	public LetterListElement(char letter, int number) {
		this.letter = letter;
		this.number = number;
	}
	public LetterListElement(char letter) {
		this(letter, 0);
	}
	public LetterListElement(int number) {
		this('\0',number);
	}
	
	
	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
