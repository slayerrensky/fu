package fu.netzsys.crawler_lucene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentByURL {
	//private ArrayList<String> getSiteContent(String urlStr){
		static String getSiteContent(String urlStr){
			URL url;
		    InputStream is = null;
		    BufferedReader br;
		    String line;
		    //ArrayList<String> linesOfContent = new ArrayList<String>();
		    String linesOfContent = "";

		    try {
		        url = new URL(urlStr);
		        is = url.openStream();  // throws an IOException
		        br = new BufferedReader(new InputStreamReader(is));

		        while ((line = br.readLine()) != null) {
		        	//linesOfContent.add(line.toLowerCase());
		            linesOfContent += line.toLowerCase();
		        }
		    } catch (MalformedURLException mue) {
		         //mue.printStackTrace();
		    } catch (IOException ioe) {
		        //ioe.printStackTrace();
		    } finally {
		        try {
		            if (is != null) is.close();
		        } catch (IOException ioe) {
		            // nothing to see here
		        }
		    }
			return linesOfContent;//.replaceAll("\\s+","");
		}
}