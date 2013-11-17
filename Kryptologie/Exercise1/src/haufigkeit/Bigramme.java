package haufigkeit;

import java.util.Comparator;

public class Bigramme implements Comparable<Bigramme>{

	public String letters;
	/**
	 * Wie oft die buchstabenkombi Promill mal vorkommt also %%
	 */
	public int haufigkeit; 
	
	public Bigramme(String letter, int haufigkeit)
	{
		this.letters = letter;
		this.haufigkeit = haufigkeit;

	}

	@Override
	public int compareTo(Bigramme o) {
		if (o.haufigkeit == haufigkeit) return 0;
		if (o.haufigkeit > haufigkeit) return 1;
		return -1;
	}
}
