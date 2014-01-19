package mastermind;

import java.util.LinkedList;
import java.util.Random;

public class MasterSolver {
	boolean started;
	LinkedList<LinkedList<Integer>> liste;
	LinkedList<Integer> givenList;
	int durchlauf;
	Random randGen;
	

	public MasterSolver(LinkedList<String> li){
		started = false;
		liste = new LinkedList<LinkedList<Integer>>();
		givenList = new LinkedList<Integer>();
		for (String string : li) {
			givenList.add(Integer.parseInt(string));
		}
		durchlauf = 0;
		randGen = new Random();
	}
	
	public void reset(){
		started = false;
		liste.clear();
		durchlauf = 0;
	}
	
	
	public void addAnswer(String input){
		if(!input.isEmpty()){
			int okay = 0;
			int wrongPos = 0;
			for(int i=0; i < input.length(); i++){
				if(input.charAt(i)=='s') okay++;
				if(input.charAt(i)=='w') wrongPos++;
			}
			if((okay+wrongPos)<4){
				//austauschen
				//1. rausfinden welcher fehlt
				int err = -1;
				for(int i=1;i<6;i++){
					if(liste.get(0).indexOf(i) < 0){
						err = i;
						break;
					}
				}
				LinkedList<Integer> tmp = new LinkedList<Integer>(liste.get(0));
				tmp.set(durchlauf, err);
				liste.add(tmp);
			}else{
				// so ab hier stimmen die zahlen, aber evtl noch in der falschen reinfolge
				LinkedList<Integer> tmp = new LinkedList<Integer>(liste.get(liste.size()-1));
				Integer integer = tmp.get(randGen.nextInt(4));
				Integer integer2 = tmp.get(randGen.nextInt(4));
				tmp.remove(integer);
				tmp.add(randGen.nextInt(4), integer);
				tmp.remove(integer2);
				tmp.add(randGen.nextInt(4), integer2);
				liste.add(tmp);
			}
		}
		durchlauf ++;
	}
	
	public String getNext(){
		if(!started){
			// init first set with genCode
			started = true;
			MasterMindGen gen = new MasterMindGen();
			liste.add(gen.easyGenINT());
		}
		return liste.get(liste.size()-1).toString().replace("[", "").replace("]", "").replace(" ", "");
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
