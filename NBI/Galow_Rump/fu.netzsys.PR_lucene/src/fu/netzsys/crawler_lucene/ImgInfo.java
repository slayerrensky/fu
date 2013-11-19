package fu.netzsys.crawler_lucene;

public class ImgInfo {
	private String alt;
	private String src;
	
	public ImgInfo(String alt, String src){
		this.alt = alt;
		this.src = src;
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
