package fu.sp.gqm.issue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		ReadAllIssues instance = new ReadAllIssues();
		instance.readAll("https://api.github.com/repos/owncloud/core/issues/");
		instance.findDoubleInTitle();
		
		
	}
}