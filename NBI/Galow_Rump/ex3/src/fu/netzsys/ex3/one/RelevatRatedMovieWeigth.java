package fu.netzsys.ex3.one;

import java.util.ArrayList;

/**
 *  die klasse soll ein Film aufnehmen und
 *  das errechnete Rating beinhalten und
 *  ene gewichtung, die sich an hand der anzahl der user richtet
 * @author Dennis
 * 
 */
public class RelevatRatedMovieWeigth {
	public Uitem item;
	public ArrayList<RatingRelatedToSim> rating;
	public Double weigth;
	
	public RelevatRatedMovieWeigth(Uitem item){
		this.item = item;
		rating = new ArrayList<RatingRelatedToSim>();
	}
	
	public void addRating(Double rating, Double sim){
		this.rating.add(new RatingRelatedToSim(rating, sim));
	}
}

class RatingRelatedToSim{
	public Double rating;
	public Double sim;
	
	public RatingRelatedToSim(Double rating, Double sim){
		this.rating = rating;
		this.sim = sim;
	}
}