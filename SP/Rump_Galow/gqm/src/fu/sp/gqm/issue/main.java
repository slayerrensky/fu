package fu.sp.gqm.issue;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		ReadAllIssues instance = new ReadAllIssues();
		instance.doitoffline = true;
		instance.readAll("https://api.github.com/repos/owncloud/core/issues/");
		System.out.println("all issues loaded."); 
		//instance.findDoubleInTitle();
		BugFIlter filter = new BugFIlter();
		filter.addAllBugs(instance.issue);
		System.out.println("found " + filter.bugedIssues.size() + " Bugs.");
	}
}