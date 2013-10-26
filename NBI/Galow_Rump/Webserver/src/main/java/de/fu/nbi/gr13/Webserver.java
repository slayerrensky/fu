package de.fu.nbi.gr13;

import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {


	int port = 80;
	public Webserver(int port) {
		this.port = port;
	}

	public void start() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				Socket connection = null;
				connection = serverSocket.accept();
				SocketThread st = new SocketThread(connection);
				Thread thread = new Thread(st);
				thread.start();
			}

		} catch (Exception e) {
			System.out.println("Webserver: " + e.getMessage());
		}
	}
}