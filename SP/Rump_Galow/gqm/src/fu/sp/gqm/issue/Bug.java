package fu.sp.gqm.issue;

import java.sql.Timestamp;
import java.util.Date;

public class Bug {
	public int bugID;
	public int issueID;
	public Timestamp createDate;
	public Timestamp closedDate;
	
	public Bug(int bugID, int issueID, String createDate, String closedDate){
		this.bugID = bugID;
		this.issueID = issueID;
		this.createDate = Timestamp.valueOf(createDate);
		if( closedDate != null){
			this.closedDate = Timestamp.valueOf(closedDate);
		}else{
			this.closedDate = null;
		}
	}
	
	public Timestamp diff() {
	    long firstTime = (getTimeNoMillis(closedDate) * 1000000) + closedDate.getNanos();
	    long secondTime = (getTimeNoMillis(createDate) * 1000000) + createDate.getNanos();
	    long diff = Math.abs(firstTime - secondTime); // diff is in nanoseconds
	    Timestamp ret = new Timestamp(diff / 1000000);
	    ret.setNanos((int) (diff % 1000000000));
	    return ret;
	}
	private long getTimeNoMillis(Timestamp t) {
	    return t.getTime() - (t.getNanos()/1000000);
	}
}
