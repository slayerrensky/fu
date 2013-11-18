package alphabetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LetterList extends ArrayList<LetterListElement>{
	
	String text = "";
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void sortByLetter(){
		Collections.sort(this,new Comparator<LetterListElement>() {

			@Override
			public int compare(LetterListElement o1, LetterListElement o2) {
				if (o1.getLetter()>o2.getLetter()) return 1;
				if (o1.getLetter()<o2.getLetter()) return -1;
				return 0;
			}
		});
	}
	
	public void sortByNumber(){
		Collections.sort(this,new Comparator<LetterListElement>() {

			@Override
			public int compare(LetterListElement o1, LetterListElement o2) {
				if (o1.getNumber()>o2.getNumber()) return -1;
				if (o1.getNumber()<o2.getNumber()) return 1;
				return 0;
			}
		});
	}
	
	public void fillWithAlphabeticLetters(){
		
		for (int i = 0;i<Alphabetic.alphabet.length;i++){
			add(new LetterListElement(Alphabetic.alphabet[i]));
		}
	}
	
	public void inkrementByLetter(char letter){
		for (int i = 0;i<size();i++){
			if (get(i).getLetter() == letter) get(i).setNumber(get(i).getNumber() + 1);
		}
	}
	
	public void removeLetter(char c){
		for (int i = 0;i<size();i++){
			if (get(i).getLetter() == c){
				remove(i);
			}
		}
	}
}
