package fu.netzsys.ex3.one;

public class SimilarUser implements Comparable<SimilarUser> {
	Uuser user1 = null;
	Uuser user2 = null;
	double similarity = -2;

	public SimilarUser(Uuser user1, Uuser user2, double similarity) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.similarity = similarity;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public Uuser getUser1() {
		return user1;
	}

	public void setUser1(Uuser user1) {
		this.user1 = user1;
	}

	public Uuser getUser2() {
		return user2;
	}

	public void setUser2(Uuser user2) {
		this.user2 = user2;
	}

	@Override
	public int compareTo(SimilarUser o) {
		if (o.getSimilarity() > this.getSimilarity())
			return 1;
		else if (o.getSimilarity() == this.getSimilarity())
			return 0;
		return -1;
	}
}