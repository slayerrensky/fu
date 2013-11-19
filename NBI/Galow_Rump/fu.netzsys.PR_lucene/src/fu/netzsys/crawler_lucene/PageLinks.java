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
	
	public void buildHMatrix(){
		int i = pages.size();
		
		int countPointsTo[] = new int[i];
		double matrix[][] = new double[i][i];
		
		for(int k=0;k<pages.size();k++){
			countPointsTo[k] = getCountOfPointingToPage(pages.get(k));
			for(int j=0;j<pages.size();j++){
				if(pages.get(j).pointsToPage(pages.get(k))){
					matrix[k][j] = pages.get(k).getPageRank() / countPointsTo[k];
				}
			}
		}
		System.out.println(matrix);
	}
}
