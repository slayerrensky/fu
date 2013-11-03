package fu.netzsys.ex3.one;

import java.util.ArrayList;

/**
 *  die klasse soll ein Film aufnehmen und
 *  das errechnete Rating beinhalten und
 *  ene gewichtung, die sich an hand der anzahl der user richtet
 * @author Dennis
 * 
 */
public class RelevantRatedItemWeigth implements Comparable<RelevantRatedItemWeigth>{
	public Uitem item;
	public Double rating;
	public boolean relevant;
	
	public RelevantRatedItemWeigth(Uitem item, Double rating, boolean relevant){
		this.item = item;
		this.rating = rating;
		this.relevant = relevant;
	}

	@Override
	public int compareTo(RelevantRatedItemWeigth arg0) {		
		if (arg0.rating == null && this.rating == null) {
		      return 0;
		    }
		    if (this.rating == null) {
		      return 1;
		    }
		    if (arg0.rating == null) {
		      return -1;
		    }
		    return arg0.rating.compareTo(this.rating);
	}
}