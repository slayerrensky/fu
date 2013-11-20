package fu.netzsys.crawler_lucene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

	public LinkedList<Page> pages;
	
	private ArrayList<String> extractTAGs(String urlStr, String content, int aktuellDepth, int maxDepth){
		ArrayList<String> list = new ArrayList<String>();

		//Pattern pattern = Pattern.compile("<([a-z][a-z0-9]*)\b[^>]*>.*?</\1>");
		Pattern pattern = Pattern.compile("\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))");
		Matcher matcher = pattern.matcher(content);
		Page page = new Page(urlStr);
		while (matcher.find()) {
			String tmp = matcher.group();
			String[] tmpA = tmp.split("\"");
			
			if(tmpA.length>1){
				String url = checkUrlIfValid(tmpA[1], urlStr);
				if(url != null){
					list.add(url);
					page.addOutLinkedPage(url);
					list = (addWithoutDoubleEntrys(list, crawl(url, aktuellDepth+1, maxDepth)));
				}				
			}
		}
		pages.add(page);
		return list;
	}
	
	public String checkUrlIfValid(String url, String mainUrl){
		
		boolean valid = false;
		if (url.startsWith("/") && !url.startsWith("//"))
		{
			String mainpage = getHost(mainUrl);
			
			System.out.println("Relative URL: " + mainpage + url);
			return mainpage + url;
		}
		if (url.startsWith("//"))
		{
			url = url.substring(2);
			valid = true;
		}
		if (url.startsWith("http://") || url.startsWith("https://"))
		{
			valid = true;
		}
		
		int slash = url.indexOf("/");
		int doubbleSlash = url.indexOf("//");
		if (doubbleSlash != -1 && slash != -1 && !valid)
		{
			if (slash <= doubbleSlash) 
			{
				valid = false;
				System.out.println("Page not Valide: " + url);
			}
		}
		else 
			valid = true;
		
		if (!valid)
			return null;
		
		Pattern pattern = Pattern.compile("(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");
		Matcher matcher = pattern.matcher(url);

		System.out.println("URL: \"" + url +"\"");
		
		if (matcher.find())
			return url;
		else
			System.out.println("URL not Valid: \"" + url +"\"");
			return null;
			
	}
	
	public static String getHost(String url){
	    if(url == null || url.length() == 0)
	        return "";

	    int doubleslash = url.indexOf("//");
	    if(doubleslash == -1)
	        doubleslash = 0;
	    else
	        doubleslash += 2;

	    int end = url.indexOf('/', doubleslash);
	    end = end >= 0 ? end : url.length();

	    return url.substring(0, end);
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
		if(aktuellDepth == 0){
			pages = new LinkedList<Page>();
		}
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
			list = extractTAGs(urlStr ,linesOfContent, aktuellDepth, maxDepth);
			return list;
		}
	}
}