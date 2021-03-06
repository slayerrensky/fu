package fu.netzsys.crawler_lucene;

import java.util.ArrayList;

public class URLInformation {
	private String title;
	private String URL;
	private String content;
	private String altInfo;
	private String meta;
	private ArrayList<ImgInfo> images;
	
	public URLInformation(){
		title = "";
		URL = "";
		content = "";
		altInfo ="";
		meta ="";
		images = new ArrayList<ImgInfo>();
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
	
	public void addImage(ImgInfo img){
		images.add(img);
	}
	
	public void addImage(String alt, String src){
		this.addImage(new ImgInfo(alt, src));
	}
	
	public ArrayList<ImgInfo> getImages(){
		return images;
	}
}
