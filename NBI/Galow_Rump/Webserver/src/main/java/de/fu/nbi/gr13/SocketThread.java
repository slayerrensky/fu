package de.fu.nbi.gr13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketThread implements Runnable {
	
	private Socket connection;

	public SocketThread(Socket s) {
		this.connection = s;
	}
	
	String workspace = "./workspace";
	String header = "";
	
	public void run() {
		try {
			System.out.println("Socket " + connection.toString() + " open");
			BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			String httpString = br.readLine();

			String currZeile = httpString;
			header = httpString;
			if (httpString == null) return;
			while (!(currZeile = br.readLine()).equals("")) {
				header += currZeile + "<br>";
		      }
//			while ((currZeile = br.readLine()) != null)
//				header = header + currZeile + "<br>";

			System.out.println("Request: " + httpString);

			String URL = httpString.split(" ")[1];
			String path = URL;

			// Parameter enthalten?
			if (httpString.contains("?")) {
				String[] urlSplitting = URL.split("\\?");
				path = urlSplitting[0];
				String parameter = urlSplitting[1];
				System.out.println("Parameter> " + parameter);

				if (parameterIs(parameter, "header", "show")) {
					System.out.println("header? -> "
							+ parameterIs(parameter, "header", "show"));
					printHeaderPage(connection);
					return;
				}
			}

			if (path.compareTo("/testpage") == 0 || path.compareTo("/testpage/") == 0)
				path = workspace + "/testpage/index.html";
			else {
				path = workspace + path;
			}

			printPage(connection, path);
		} catch (FileNotFoundException e) {
			try {
				printPage(connection, workspace
						+ "/testpage/error/404.html");
			} catch (IOException e1) {
				System.out.println("Error printErrorPage: " + e1.getMessage());
				return;
			}
			System.out.println("Error File not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Other: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println("Socket " + connection.toString() + " close");
				return;
			} catch (IOException e) {
				System.out.println("Error Connection close: " + e.getMessage());
			}
		}
	}

private void printHeaderPage(Socket connection) throws IOException {
	PrintWriter pw = new PrintWriter(connection.getOutputStream());
	pw.println("HTTP/1.0 200 Ok\n" + "Content-type: text/html\n\n"
			+ "<HTML><HEAD><TITLE>Hello</TITLE></HEAD>\n"
			+ "<BODY><H1>Willkommen</H1>\n" + "<P>Das HTTP Kommando war:\n"
			+ "<PRE>\n" + header + "\n</PRE>\n</BODY></HTML>\n");
	pw.flush();

}

private void printPage(Socket connection, String path) throws IOException {
	PrintWriter pw = new PrintWriter(connection.getOutputStream());
	BufferedReader br_file = new BufferedReader(new FileReader(path));

	String currZeile = "";
	while ((currZeile = br_file.readLine()) != null)
		pw.print(currZeile);

	br_file.close();
	pw.flush();
}

private boolean parameterIs(String paramString, String param, String value) {
	try {
		String[] seperateParameter = paramString.split("\\&");

		for (int i = 0; i < seperateParameter.length; i++) {
			if (seperateParameter[i].split("=")[0].equals(param)) {
				if (seperateParameter[i].split("=")[1].equals(value))
					return true;
			}
		}
		return false;
	} catch (Exception e) {
		return false;
	}
}
}
