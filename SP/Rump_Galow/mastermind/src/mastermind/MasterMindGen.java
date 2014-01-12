package mastermind;

import java.util.LinkedList;
import java.util.Random;

public class MasterMindGen {
	private LinkedList<String> givenColours;
	private Random randGen; 
	
	public MasterMindGen(){
		super();
		randGen = new Random();
		
		givenColours = new LinkedList<String>();
		givenColours.add("grün");
		givenColours.add("rot");
		givenColours.add("blau");
		givenColours.add("gelb");
		givenColours.add("orange");
		givenColours.add("violett");
	}
	
	public LinkedList<String> gen(){
		LinkedList<String> farbCode = new LinkedList<String>();
		for(int i=0;i<4;i++){
			farbCode.add(givenColours.get(randGen.nextInt(6)));
		}
		
		return farbCode;
	}

}
