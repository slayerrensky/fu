package mastermind;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("mastermind start.");
		MasterMindGen gen = new MasterMindGen();
		for(int i=0;i<10;i++){
			System.out.println(gen.gen().toString());
		}
	}

}
