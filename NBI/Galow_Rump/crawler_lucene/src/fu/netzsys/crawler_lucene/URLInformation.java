package fu.netzsys.crawler_lucene;

public class URLInformation {
	private String title;
	private String URL;
	private String content;
	private String altInfo;
	private String meta;
	
	public URLInformation(){
		title = "";
		URL = "";
		content = "";
		altInfo ="";
		meta ="";
	}
	
	public String getURL() {
		return URL;
	}
	
	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAltInfo() {
		return altInfo;
	}
	
	public void setAltInfo(String altInfo) {
		this.altInfo = altInfo;
	}
	
	public String getMeta() {
		return meta;
	}
	
	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
