package de.fu.nbi.gr13;


public class Main {

	public static void main(String[] args){
		System.out.println("Java Programm von Peter Kaminski (4800158) und Lars Willrich (4748825)");

		int port = 80;
		try{
			port = Integer.parseInt(args[0]);
		}catch (Exception e){
			System.out.println(e.getMessage() + " - Setting 80 as port number");
		}
		Webserver server = new Webserver(port);
		
		server.start();
	}
}
