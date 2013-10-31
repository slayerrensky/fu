package fu.netzsys.ex3.one;

public class Udata {
//	u.data     -- The full u data set, 100000 ratings by 943 users on 1682 items.
//    Each user has rated at least 20 movies.  Users and items are
//    numbered consecutively from 1.  The data is randomly
//    ordered. This is a tab separated list of 
//   user id | item id | rating | timestamp. 
//    The time stamps are unix seconds since 1/1/1970 UTC   

	public int rating;
	public int unixTimestamp;
	public Uitem item;
	public Uuser user;
	
	public Udata(Uuser user, Uitem item, int rating, int unixTimestamp){
		this.user = user;
		this.item = item;
		this.rating = rating;
		this.unixTimestamp = unixTimestamp;
	}

}
