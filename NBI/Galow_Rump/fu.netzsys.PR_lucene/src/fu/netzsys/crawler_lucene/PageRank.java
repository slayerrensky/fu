package fu.netzsys.crawler_lucene;

import java.util.LinkedList;

public class PageRank {
	public double alpha;	// Daempfungsfaktor
	public double epsilon;	// minimum Value
	public PageLinks pageLinks;
	
	public PageRank(PageLinks pageLinks) {
		this.pageLinks = pageLinks;
	}
	
	public void setAlpha(double alpha){
		this.alpha = alpha;
	}
	
	public void setEpsilon(double epsilon){
		this.epsilon = epsilon;
	}
	
	public void calculatePageRank(String pageName){
		Page page =  pageLinks.getPageByName(pageName);
		calculatePageRank(page);
		
	}
	
	public void calculatePageRank(Page page){
		double pageRank = 0.0;
		pageRank = ((1-alpha)/pageLinks.getPageCount()) + alpha;
		//Page page =  pageLinks.getPageByName(pageName);
		
		LinkedList<Page> listOfPage = pageLinks.getPagesThatPointsToMe(page);
		double sum = 0.0;
		
		for (Page listPage : listOfPage) {
			sum += (listPage.getPageRank()/listPage.getCountOfOutLinks());
		}
		
		pageRank = (pageRank * sum) + epsilon;
		
		page.setPageRank(pageRank);
	}
	
	public void calculateAllPageRanks(){
		for (Page page : pageLinks.getAllPages()) {
			calculatePageRank(page);
			System.out.println(String.format("%10s: %1.4f", page.getName(), page.getPageRank()));
		}
	}
	
	public void calculate(int i){
		for(int k = 0;k<i;k++){
			calculateAllPageRanks();
			System.out.println(String.format("Durchlauf %d", k));
		}
	}
}
