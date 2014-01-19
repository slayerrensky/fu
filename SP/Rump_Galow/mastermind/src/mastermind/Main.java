package mastermind;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("mastermind start.");
		System.out.println("Mode 1 or 2?: ");
		Scanner in = new Scanner(System.in);
		int mode = Integer.parseInt(in.nextLine());
		String tmp = "";
		if(mode==2){
			System.out.print("Ihre Eingabe: ");
			tmp = in.nextLine();
		}
		MasterMindGameLogic mastermind = new MasterMindGameLogic(mode, tmp);
		mastermind.startGame();
	}

}
