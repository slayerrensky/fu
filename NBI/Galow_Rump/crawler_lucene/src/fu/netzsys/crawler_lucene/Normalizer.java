package fu.netzsys.crawler_lucene;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SizeRequirements;
import javax.swing.SizeSequence;


public class Normalizer {
	
	public URLInformation normalize(String str, String url){
		URLInformation siteInfo = new URLInformation();
		siteInfo.setURL(url);
		str = getImg(str, siteInfo);
		str = getTitle(str, siteInfo);
		str = extract_ALT_Attributs(str, siteInfo);
		str = extractMetaTags(str, siteInfo);
		str = removePunctuation(str, siteInfo);
        str = removeTags(str, siteInfo);
		return siteInfo;
	}
	
	private String removeTags(String str, URLInformation siteInfo){
		str = str.replaceAll("<[^>]*>", "");
		siteInfo.setContent(str);
		return str;
	}
	
	private String extract_ALT_Attributs(String str, URLInformation siteInfo){
		String line = new String();
		
		Pattern pattern = Pattern.compile("\\s*(?i)alt\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String tmp = matcher.group();
			String[] tmpA = tmp.split("\"");
			if(tmpA.length>1){
				line += tmpA[1] + ";";
			}
		}
		System.out.println("alt info: "+line);
		siteInfo.setAltInfo(line);
		return str;
	}
	
	private String extractMetaTags(String str, URLInformation siteInfo){
		String line = new String();
		Pattern pattern = Pattern.compile("\\s*(?i)meta[\\w\\s]*(?i)content\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String tmp = matcher.group();
			String[] tmpA = tmp.split("\"");
			if(tmpA.length>1){
				line += tmpA[1] + ";";
			}
		}
		System.out.println("meta info: "+line);
		siteInfo.setMeta(line);
		return str;
	}
	
	private String removePunctuation(String str, URLInformation siteInfo){
		return str;
	}
	
	private String getImg(String str, URLInformation siteInfo)
	{

		Pattern pattern = Pattern.compile("<img[\\s\\w/.=\"-]*>");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String tmp = matcher.group();
			String alt = attributValue(tmp, "alt");
			String src = attributValue(tmp, "src");
			if (src.startsWith("/") && !src.startsWith("//"))
			{
				String url = siteInfo.getURL();
				if (url.endsWith("/"))
				{
					src = url.substring(0, url.length()-1) + src;
				}
				else
				{
					src = getHost(siteInfo.getURL()) + src;
				}
				
			}
			if (src.startsWith("//"))
			{
				src = src.substring(2);
			}
			
			System.out.println("Alt: \"" + alt + "\"" + "src: \"" + src +"\"");
			siteInfo.getImages().add(new ImgInfo(alt, src));
		}
				
		return str;
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

	    return url.substring(doubleslash, end);
	}
	
	private String attributValue(String str,String name)
	{
		String line = new String(); 
		Pattern pattern = Pattern.compile("\\s*(?i)"+ name +"\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String tmp = matcher.group();
			String[] tmpA = tmp.split("\"");
			if(tmpA.length>1){
				line += tmpA[1];
			}
		}
		return line;
	}
	
	private String getTitle(String str, URLInformation siteInfo){
		String titleTag = "<title[^>]*>(.*)</title>";
        Pattern pattern = Pattern.compile(titleTag);
        Matcher matcher = pattern.matcher(str);
        String title = null;
        if (matcher.find()){
                title = matcher.group();
                title = title.replaceAll("<title>", "").replaceAll("</title>", "");
                System.out.println("title info: "+title);
				siteInfo.setTitle(title);
        }
		return str;
	}
}
