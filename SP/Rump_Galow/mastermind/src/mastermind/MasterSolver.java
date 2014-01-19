package mastermind;

import java.util.LinkedList;

public class MasterSolver {
	boolean started;
	LinkedList<LinkedList<Integer>> liste;

	public MasterSolver(){
		started = false;
		liste = new LinkedList<LinkedList<Integer>>();
	}
	
	public void reset(){
		started = false;
		liste.clear();
	}
	
	public String solve(String input){
		if(!started){
			// init first set with genCode
			started = true;
			MasterMindGen gen = new MasterMindGen();
			liste.add(gen.easyGenINT());
			return IntlistToStr(liste.get(0));
		}
		if(!input.isEmpty()){
			int okay = 0;
			int wrongPos = 0;
			for(int i=0; i < input.length(); i++){
				if(input.charAt(i)=='s') okay++;
				if(input.charAt(i)=='w') wrongPos++;
			}
		}
		return "";
	}
	
	public String IntlistToStr(LinkedList<Integer> li){
		String str = "";
		for (int i=0;i<li.size();i++) {
			if(i != 0){
				str += ",";
			}
			str += li.get(i).toString();
		}
		return str;
	}
}
