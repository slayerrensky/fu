package fu.netzsys.crawler_lucene;

public class ImgInfo {
	private String title;
	private String alt;
	private String src;
	
	public ImgInfo(String title, String alt, String src){
		this.title = title;
		this.alt = alt;
		this.src = src;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getAlt(){
		return alt;
	}
	
	public void setAlt(String alt){
		this.alt = alt;
	}
	
	public String getSrc(){
		return src;
	}
	
	public void setSrc(String src){
		this.src = src;
	}
	
}
