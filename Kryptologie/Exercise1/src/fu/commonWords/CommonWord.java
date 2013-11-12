package fu.commonWords;

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
	
	
	
	
}
