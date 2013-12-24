package fu.sp.gqm.issue;

public class main {

	public static void main(String[] args) {

		ReadAllIssues instance = new ReadAllIssues();
		instance.doitoffline = true;
		instance.readAll("https://api.github.com/repos/owncloud/core/issues/");
		//instance.findDoubleInTitle();
		BugFIlter filter = new BugFIlter();
		filter.addAllBugs(instance.issue);
		filter.categorizeBug();
		filter.calculateAllCloseTimeDiff();
		filter.calcuateAverageBugCloseTime();
	}
}