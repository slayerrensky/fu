package mastermind;

import java.util.LinkedList;
import java.util.Scanner;

public class MasterMindGameLogic {
	private LinkedList<String> masterCode;
	private boolean won;
	private MasterMindGen gen;
	
	public MasterMindGameLogic(){
		gen = new MasterMindGen(); 
	}
	
	public void startGame(){
		//masterCode = gen.gen();
		masterCode = gen.easyGen();
		won = false;

		System.out.println("MasterCode generated " + masterCode.toString());
		System.out.println("Available Colors: "+gen.getAvailableColors());
		
		Scanner in = new Scanner(System.in);
		
		for(int i=1;i<11;i++){
			System.out.print("Versuch " + i + "  ");
			System.out.print("Eingagbe: ");
			String answer = checkInput(in.nextLine());
			if(!answer.isEmpty()){
				System.out.println("Antwort: " + answer);
				if(answer.contentEquals("ssss")){
					won = true;
					break;
				}
				
			}else{
				System.out.println("no correct input");
				// schritt zurück
				i--;
			}
		}
		
		if(won){
			System.out.println("you won!");
		}
		else{
			System.out.println("you lose!");
		}
		
	}
	
	private String checkInput(String inStr){
		inStr.replaceAll(".", ",");
		inStr.replaceAll("-", ",");
		String[] tmp = inStr.split(",");
		if(tmp.length != 4){
			return "";
		}
		if(!colorsAreCorrect(tmp)){
			return "";
		}
		String answer= checkCode(tmp);
		for(int i=answer.length();i<4;i++) answer += "-";
		return answer;
	}
	
	private boolean colorsAreCorrect(String[] strArr){
		if((gen.getAvailableColors().contains(strArr[0].toLowerCase())) && (gen.getAvailableColors().contains(strArr[1].toLowerCase())) && (gen.getAvailableColors().contains(strArr[2].toLowerCase())) && (gen.getAvailableColors().contains(strArr[3].toLowerCase()))){
			return true;
		}else return false;
	}
	
	private boolean isIn(int[] intArr, int number){
		for(int i=0;i<4;i++){
			if(intArr[i] == number) return true;
		}
		return false;
	}
	
	private String checkCode(String[] strArr){
		int found[] = {-1, -1, -1, -1};
		
		String posOk = "";
		String posNotCorrect = "";
		int i = 0;

		for(int k = 0;k<4;k++){
			if(masterCode.get(k).equals(strArr[k])){
				found[i++] = k;
				posOk += "s";
			}else{
				for(int q=0;q<4;q++){
					if( masterCode.get(k).equals(strArr[q]) && (! isIn(found, q)) ){
						posNotCorrect += "w";
					}
				}
			}
		}
		return posOk+posNotCorrect;
	}
}
