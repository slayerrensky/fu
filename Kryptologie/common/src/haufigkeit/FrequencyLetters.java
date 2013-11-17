package haufigkeit;

public class FrequencyLetters implements Comparable<FrequencyLetters> {
	
	private String letters;
	private int frequency;
	private double frequencyInPercent;
	
	public FrequencyLetters(String letters)
	{
		this.letters = letters;
	}

	public String getLetters() {
		return letters;
	}

	public void setLetters(String letters) {
		this.letters = letters;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public double getFrequencyInPercent() {
		return frequencyInPercent;
	}

	public void setFrequencyInPercent(double frequencyInPercent) {
		this.frequencyInPercent = frequencyInPercent;
	}

	@Override
	public int compareTo(FrequencyLetters arg0) {
		if (arg0.getFrequency() < this.getFrequency())
			return 1;
		else if (arg0.getFrequency() < this.getFrequency())
			return -1;
		else
			return 0;
		
	}
}
