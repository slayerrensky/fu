package fu.netzsys.crawler_lucene;

import java.util.LinkedList;

public class Page {
	public String pageName;
	public double pageRank;
	LinkedList<String> outLinks;

	public Page(String name){
		this.pageName = name;
		this.pageRank = 1;
		outLinks = new LinkedList<String>();
	}
	
	public String getName(){
		return pageName;
	}
	
	public double getPageRank(){
		return pageRank;
	}
	
	public void setPageRank(double rank){
		pageRank = rank;
	}
	
	public Boolean isDanglingNode(){
		if(outLinks.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void addOutLinkedPage(String name){
		outLinks.add(name);
	}
	
	public void removeOutLinkedPage(String name){
		outLinks.remove(name);
	}
	
	public Boolean pointsToPage(Page page){
		return pointsToPage(page.getName());
	}
	
	public Boolean pointsToPage(String name){
		for (String oneName : outLinks) {
			if(oneName.equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public int getCountOfOutLinks(){
		return outLinks.size();
	}
}
