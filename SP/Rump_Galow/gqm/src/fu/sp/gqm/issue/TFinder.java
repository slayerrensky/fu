package fu.sp.gqm.issue;

import java.util.ArrayList;

public class TFinder implements Runnable
{
	int start; 
	int end;
	ArrayList<Issue> issue;
	private ArrayList<IssuePair> issuePair;
	boolean ready = false; 
	
	
	public  TFinder(int start, int end, ArrayList<Issue> issue)
	{
		this.start = start;
		this.end =end;
		this.issue = issue;
		issuePair = new ArrayList<IssuePair>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 
		
		for (int i = start; i < end; i++ ) {
			for (int k = i+1; k < issue.size(); k++ ) {
				String[] i1s = issue.get(i).title.split(" ");
				String[] i2s = issue.get(k).title.split(" ");
				
				IssuePair ip = new IssuePair(issue.get(i), issue.get(k)); 
				for (String s1 : i1s) {
					for (String s2 : i2s) {
						if (s1.compareTo(s2) == 0)
						{
							ip.counter++;
						}
					}
				}
				issuePair.add(ip);
			}
			ready = true;
		}
	}

	public ArrayList<IssuePair> getIssuePair() {
		while (!ready)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return issuePair;
	}

	public void setIssuePair(ArrayList<IssuePair> issuePair) {
		this.issuePair = issuePair;
	}
	
		
}
