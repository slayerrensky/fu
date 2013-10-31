package fu.netzsys.ex3.one;

public class Uuser {
	// u.user -- Demographic information about the users; this is a tab
	// separated list of
	// user id | age | gender | occupation | zip code
	// The user ids are the ones used in the u.data data set.

	int id;

	public Uuser(int id) {
		super();
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id + "";
	}
}
