package fu.sp.gqm.issue;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.text.html.HTML.Tag;

import com.google.gson.Gson;

public class ReadAllIssues {
	
	ArrayList<Issue> issue;
	Gson g;
	//public WebDriver driver;
	
	public ReadAllIssues()
	{
		issue = new ArrayList<Issue>();
		g = new Gson();
	}
	
	public void readAll(String home)
	{
		boolean noError = true;
		boolean readfromFile = false;
		int counter = 1 -1;
		//String path = getClass().getClassLoader().getResource(".").getPath();
		String path = System.getProperty("user.dir");
		IssueToFile itf = new IssueToFile(path +"/data");
		
		while (noError && counter < 7000)
		{
			readfromFile = false;
			counter++;
			if (itf.IssueFileExist("Issue" + counter +".json") )
			{
				readfromFile = true;
				System.out.print("Filesystem: ");
			}
			else
				System.out.print("WWW: ");
				
			String content;
			if (readfromFile)
			{
				content = itf.readFromIssue(counter);
			}
			else
			{
				content = ContentByURL.getSiteContent(home + counter + "?access_token=89d91aa173d479447869f83b44df0cc068209476");
			}
			if (content.compareTo("") == 0 )
			{
				
			} else {
				try {
					Issue i = g.fromJson(content, Issue.class);
					i.setNumber(counter);
					i.setRawIssue(content);
					String comments;
					if (readfromFile)
					{
						comments = itf.readFromComment(counter);
					}
					else
					{
						comments = ContentByURL.getSiteContent(i.getComments_url() + "?access_token=89d91aa173d479447869f83b44df0cc068209476");
					}
					i.setRawComment(comments);
					IssueComment[] com = g.fromJson(comments, IssueComment[].class);
					i.setComment(new ArrayList<IssueComment>(Arrays.asList(com)) );
					issue.add(i);
					itf.writeToIssueFile("Issue"+counter+".json",i.getRawIssue());
					itf.writeToCommentFile("Comment"+counter+".json",i.getRawComment());
					
					System.out.println(i.title);	
				}
				catch (Exception ex)
				{
					System.out.println(ex.toString());
					noError = true;
					break;
				}
				
			}	
		}
		System.out.println("Issues Read: " + issue.size());
	}
	
	public void findDoubleInTitle()
	{
		ArrayList<IssuePair> issuePair = new ArrayList<IssuePair>();
		TFinder tf = new TFinder(0, issue.size(), issue);
		tf.run();
		issuePair = tf.getIssuePair();
		
		
		
	    Collections.sort(issuePair);
		for (int i = 0; i<100;i++)
		{
			System.out.println(i + ". " + issuePair.get(i).counter + "x " + 
						issuePair.get(i).i1.getNumber() + "(" + issuePair.get(i).i1.title + ") " +
						issuePair.get(i).i2.getNumber() + "(" + issuePair.get(i).i2.title +")" ); 
		}
	}
	

}
