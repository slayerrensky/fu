package fu.netzsys.crawler_lucene;

public class PageRank {
	public double alpha;	// Daempfungsfaktor
	public double epsilon;	// minimum Value
	
	public PageRank() {
		// TODO Auto-generated constructor stub
	}
	
	public void setAlpha(double alpha){
		this.alpha = alpha;
	}
	
	public void setEpsilon(double epsilon){
		this.epsilon = epsilon;
	}
}
