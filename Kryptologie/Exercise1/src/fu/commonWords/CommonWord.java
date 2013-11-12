package fu.commonWords;

import java.util.ArrayList;

public class CommonWord {

	private String Word;
	private int Frequency;
	private int Rank;
	
	public CommonWord(String word, int frequency, int rank ){
		setWord(word);
		setFrequency(frequency);
		setRank(rank);
	}

	public String getWord() {
		return Word;
	}

	public void setWord(String word) {
		Word = word;
	}

	public int getFrequency() {
		return Frequency;
	}

	public void setFrequency(int frequency) {
		Frequency = frequency;
	}

	public int getRank() {
		return Rank;
	}

	public void setRank(int rank) {
		Rank = rank;
	}
	
	public static int countWords(ArrayList<CommonWord> wordList, String chiffreText){
		String decryptText = chiffreText.replace(" ", "");
		int found = 0;
		for (CommonWord cw : wordList) {
			if (decryptText.contains(cw.getWord()))
			{
				int index = 0;
				while (index != -1)
				{
					index = decryptText.indexOf(cw.Word, index + cw.Word.length());
					if (index != -1 )
						found++;
				}
			}
		}
		return found;
	}
	
	
	
	
}
