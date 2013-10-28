package fu.netzsys.crawler_lucene;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Normalizer {
	
	public URLInformation normalize(String str){
		URLInformation siteInfo = new URLInformation();
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
		Pattern pattern = Pattern.compile("\\s*<meta[\\w\\s]*(?i)content\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))");
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
	
	private String getTitle(String str, URLInformation siteInfo){
		String title = str.replaceAll("<title>[^>]*>", "");
		siteInfo.setTitle(title);
		return str;
	}
}
