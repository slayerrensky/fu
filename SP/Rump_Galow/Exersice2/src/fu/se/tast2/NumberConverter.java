package fu.se.tast2;

public class NumberConverter {
	
	public NumberConverter()
	{
		
	}
	
	public String getAsString(int number)
	{
		
		switch (number)
		{
		case 0: return new String("zero");
		case 1: return new String("one");
		case 2: return new String("tow");
		case 3: return new String("three");
		case 4: return new String("four");
		case 5: return new String("five");
		case 6: return new String("six");
		case 7: return new String("seven");
		case 8: return new String("eight");
		case 9: return new String("nine");
		case 10: return new String("ten");
		default: return null;
		}
		
	}
}
