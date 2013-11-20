package fu.netzsys.crawler_lucene;

import java.util.LinkedList;

public class PageLinks {
	LinkedList<Page> pages;
	
	public PageLinks(){
		pages = new LinkedList<Page>();
	}
	
	public void addPage(Page page){
		pages.add(page);
	}
	
	public void removePage(Page page){
		pages.remove(page);
	}
	
	public int getCountOfPointingToPage(Page page){
		int counter = 0;
		
		for (Page onePage : pages) {
			if(onePage.pointsToPage(page.getName())){
				counter++;
			}
		}
		return counter;
	}
	
	public void initHMatrix(){
		int i = pages.size();
		
		int countPointsTo[] = new int[i];
		double matrix[][] = new double[i][i];
		
		for(int k=0;k<pages.size();k++){
			countPointsTo[k] = getCountOfPointingToPage(pages.get(k));
			for(int j=0;j<pages.size();j++){
				if(pages.get(j).pointsToPage(pages.get(k))){
					matrix[k][j] = pages.get(k).getPageRank() / countPointsTo[k];
				}else{
					matrix[k][j] = 0;
				}
			}
		}
		System.out.println(matrix);
	}
	
	public int getPageCount(){
		return pages.size();
	}
	
	public double getPageRank(String name){
		for (Page page : pages) {
			if(name.equals(page.getName())){
				return page.getPageRank();
			}
		}
		return 0.0;
	}
	
	public Page getPageByName(String name){
		for (Page page : pages) {
			if(name.equals(page.getName())){
				return page;
			}
		}
		return null;
	}
	
	public LinkedList<Page> getPagesThatPointsToMe(Page page){
		LinkedList<Page> setOfPages = new LinkedList<Page>();
		
		for (Page listPage : pages) {
			if(listPage.pointsToPage(page)){
				setOfPages.add(listPage);
			}
		}
		return setOfPages;
	}
	
	public LinkedList<Page> getAllPages(){
		return pages;
	}
	
	public void addAll(LinkedList<Page> pages){
		this.pages.addAll(pages);
	}
}
