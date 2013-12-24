package fu.sp.gqm.issue;

import java.sql.Timestamp;
import java.util.ArrayList;

public class BugFIlter {
	ArrayList<Issue> bugedIssues;
	ArrayList<Bug> bugs;
	ArrayList<Timestamp> difftimestamps;
	public BugFIlter()
	{
		bugedIssues = new ArrayList<Issue>();
		difftimestamps = new ArrayList<Timestamp>();
		bugs = new ArrayList<Bug>();
	}
	
	public void addAllBugs(ArrayList<Issue> issues){
		
		for (Issue issue : issues) {
			if(issue.hasLabel("Bug")){
				bugedIssues.add(issue);
			}
		}
	}
	
	public void categorizeBug(){
		for (Issue issuewithbug : bugedIssues) {
			if(issuewithbug.closed_at == null){
				//bugedIssuesTheyOpen.add(issuewithbug);
				bugs.add(new Bug(0,issuewithbug.getNumber(), issuewithbug.created_at.replace('t', ' ').substring(0, 19), null));
			}else{
				//bugedIssuesTheyClosed.add(issuewithbug);
				bugs.add(new Bug(0,issuewithbug.getNumber(), issuewithbug.created_at.replace('t', ' ').substring(0, 19), issuewithbug.closed_at.replace('t', ' ').substring(0, 19)));
			}
		}
	}
	
	public void calculateAllCloseTimeDiff(){
		for (Bug bug : bugs) {
			if(bug.closedDate != null){
				Timestamp t = bug.diff();
				difftimestamps.add(t);
				//System.out.println(String.format("[%4d]Zeit: %2dJ %2dM %2dT %2dS %2dM", bug.issueID, t.getYear() - 70, t.getMonth(), t.getDay(), t.getHours(), t.getMinutes()));
			}
		}
	}
	
	public void calcuateAverageBugCloseTime(){
		int Y=0,M=0,D=0,H=0,MM=0;
		for (Timestamp t : difftimestamps) {
			Y+=t.getYear() - 70;
			M+=t.getMonth();
			D+=t.getDay();
			H+=t.getHours();
			MM+=t.getMinutes();
		}
		Y/=difftimestamps.size();
		M/=difftimestamps.size();
		D/=difftimestamps.size();
		H/=difftimestamps.size();
		MM/=difftimestamps.size();
		System.out.println(String.format("Durchschnittliche bearbeitungszeit: %2dJ %2dM %2dT %2dS %2dM", Y, M, D, H, MM));
	}
	
	
}
