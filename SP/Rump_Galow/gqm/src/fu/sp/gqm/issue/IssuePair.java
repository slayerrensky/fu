package fu.sp.gqm.issue;

public class IssuePair implements Comparable<IssuePair> 
{
	public int counter;
	public Issue i1;
	public Issue i2;
	public double prozent;
	
	public IssuePair(Issue i1, Issue i2 )
	{
		this.i1 = i1;
		this.i2 = i2;
		this.counter = 0;
	}
	
	@Override
	public int compareTo(IssuePair o) {
		if (this.counter < o.counter)
			return 1;
		else if (this.counter > o.counter)
			return -1;
		else
			return 0;
	}
}
