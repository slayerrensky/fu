package fu.netzsys.crawler_lucene;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
	//private ArrayList<String> extractTAGs(ArrayList<String> content){
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
				//list.add(tmpA[1]);
			}
		}
		return list;
	}
	
	/*public ArrayList<String> addWithoutDoubleEntrys(ArrayList<String> list, ArrayList<String> listNEW){
		ArrayList<String> returnList = new ArrayList<String>(list);
		Iterator<String> it = listNEW.iterator();
		
		while(it.hasNext()){
			String tmp = it.next();
			if(returnList.indexOf(tmp) == -1){
				returnList.add(tmp);
			}
		}
		
		return returnList;
	}*/
	
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
	
	public ArrayList<Crawled_URL> transformWithDepth_info(ArrayList<String> list, int depth){
		ArrayList<Crawled_URL> crawled_list = new ArrayList<Crawled_URL>();
		for (String urlStr : list) {
			crawled_list.add(new Crawled_URL(depth,urlStr));
		}
		return crawled_list;
	} 
	
	public ArrayList<String> transform(ArrayList<Crawled_URL> list){
		ArrayList<String> strList = new ArrayList<String>();
		for (Crawled_URL crawled_URL : list) {
			strList.add(crawled_URL.url);
		}
		
		return strList;
	} 
	
	
	public ArrayList<String> crawlAll(String urlStr, int maxDepth){
		
		ArrayList<Crawled_URL> list = transformWithDepth_info(crawl(urlStr), 0);
		
		for(int y=0;y<list.size();y++){
			if(list.get(y).depth<maxDepth){
				list.addAll(addWithoutDoubleEntrys(list, transformWithDepth_info(crawl(list.get(y).url), list.get(y).depth+1)));
			}
		}

		//int counter = 0;
		
		/*while(counter < list.size()){
			list = addWithoutDoubleEntrys(list, crawl(list.get(counter)));
			counter++;
		}*/
		
				
		/*
		
		
		for(int i=0;i<list.size();i++){
			ArrayList<String> list_depth2 = crawl(urlStr);
			for(int k=0;k<list_depth2.size();k++){
				list = addWithoutDoubleEntrys(list, crawl(list_depth2.get(counter)));
			}
		}*/
		
		System.out.println(list.toString());
		
		return transform(list);		
	}
}
