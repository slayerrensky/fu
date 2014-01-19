package mastermind;

import java.util.LinkedList;
import java.util.Random;

public class MasterMindGen {
	private LinkedList<String> givenColors;
	private Random randGen; 
	
	public MasterMindGen(){
		super();
		randGen = new Random();
		
		givenColors = new LinkedList<String>();
		givenColors.add("gr");
		givenColors.add("rt");
		givenColors.add("bl");
		givenColors.add("ge");
		givenColors.add("or");
		givenColors.add("vi");
	}
	
	public LinkedList<String> gen(){
		LinkedList<String> farbCode = new LinkedList<String>();
		for(int i=0;i<4;i++){
			farbCode.add(givenColors.get(randGen.nextInt(6)));
		}
		
		return farbCode;
	}
	
	public LinkedList<String> easyGen(){
		LinkedList<String> farbCode = new LinkedList<String>();
		LinkedList<String> restFarben = new LinkedList<String>(givenColors);
		
		for(int i=0;i<4;i++){
			int k = randGen.nextInt(restFarben.size());
			farbCode.add(restFarben.get(k));
			restFarben.remove(k);
		}
		
		return farbCode;
	}
	
	public LinkedList<String> getAvailableColors(){
		return givenColors;
	} 
}
