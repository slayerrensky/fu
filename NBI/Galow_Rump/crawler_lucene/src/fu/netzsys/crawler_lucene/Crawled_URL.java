package fu.netzsys.crawler_lucene;

import java.util.ArrayList;

public class Crawled_URL {
	public int depth;
	public String url;
	public ArrayList<String> list;
	
	public Crawled_URL(int depth, String url){
		this.depth = depth;
		this.url = url;
	}

}
