package fu.netzsys.crawler_lucene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
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
		String linesOfContent = ContentByURL.getSiteContent(urlStr);
		if(linesOfContent.isEmpty()){
			return new ArrayList<String>();
		}else{
			list = extractTAGs(linesOfContent);
			return list;
		}
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
