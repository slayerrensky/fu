package fu.netzsys.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;


public class Crawler {
	
	//private ArrayList<String> getSiteContent(String urlStr){
	private String getSiteContent(String urlStr){
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
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
		return linesOfContent;//.replaceAll("\\s+","");
	}
	
	//private ArrayList<String> extractTAGs(ArrayList<String> content){
	private ArrayList<String> extractTAGs(String content){
		ArrayList<String> list = new ArrayList<String>();

		//Pattern pattern = Pattern.compile("<([a-z][a-z0-9]*)\b[^>]*>.*?</\1>");
		Pattern pattern = Pattern.compile("\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String tmp = matcher.group();
			String[] tmpA = tmp.split("\"");
			if(tmpA.length>1){
				list.add(tmpA[1]);
			}
		}
		return list;
	}
	
	public ArrayList<String> addWithoutDoubleEntrys(ArrayList<String> list, ArrayList<String> listNEW){
		ArrayList<String> returnList = new ArrayList<String>(list);
		Iterator<String> it = listNEW.iterator();
		
		while(it.hasNext()){
			String tmp = it.next();
			if(returnList.indexOf(tmp) == -1){
				returnList.add(tmp);
			}
		}
		
		return returnList;
	}
	
	public ArrayList<String> crawl(String urlStr){
		ArrayList<String> list = new ArrayList<String>();
		String linesOfContent = getSiteContent(urlStr);
		list = extractTAGs(linesOfContent);
		return list;
	}
	
	public ArrayList<String> crawlAll(String urlStr){
		ArrayList<String> list = crawl(urlStr);
		int counter = 0;
		
		while(counter < list.size()){
			list = addWithoutDoubleEntrys(list, crawl(list.get(counter)));
			counter++;
		}
		System.out.println(list.toString());
		
		return list;		
	}
}
