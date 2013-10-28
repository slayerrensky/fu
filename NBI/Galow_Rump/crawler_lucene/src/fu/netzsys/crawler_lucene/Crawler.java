package fu.netzsys.crawler_lucene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

	private ArrayList<String> extractTAGs(String content, int aktuellDepth, int maxDepth){
		ArrayList<String> list = new ArrayList<String>();

		//Pattern pattern = Pattern.compile("<([a-z][a-z0-9]*)\b[^>]*>.*?</\1>");
		Pattern pattern = Pattern.compile("\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String tmp = matcher.group();
			String[] tmpA = tmp.split("\"");
			if(tmpA.length>1){
				list.add(tmpA[1]);
				list = (addWithoutDoubleEntrys(list, crawl(tmpA[1], aktuellDepth+1, maxDepth)));
			}
		}
		return list;
	}
	
	public ArrayList<String> addWithoutDoubleEntrys(ArrayList<String> list, ArrayList<String> listNEW){
	ArrayList<String> returnList = new ArrayList<String>(list);
	Iterator<String> it = listNEW.iterator();
	
	while(it.hasNext()){
		String tmp = it.next();
		if(list.indexOf(tmp) == -1){
			returnList.add(tmp);
		}
	}
	return returnList;
	}
	
	public ArrayList<String> crawl(String urlStr, int aktuellDepth, int maxDepth){
		System.out.println("crawlSite: " + urlStr);
		ArrayList<String> list = new ArrayList<String>();
		if(aktuellDepth >= maxDepth){
			System.out.println("max depth reached.");
			return list;
		}
		String linesOfContent = ContentByURL.getSiteContent(urlStr);
		if(linesOfContent.isEmpty()){
			return new ArrayList<String>();
		}else{
			list = extractTAGs(linesOfContent, aktuellDepth, maxDepth);
			return list;
		}
	}
}