package fu.sp.gqm.issue;

import java.util.ArrayList;

public class Issue {
	
	String comments_url;
	private ArrayList<IssueComment> comment;
	private String rawComment; 
	String title;
	User user;
	String body;
	public ArrayList<Labels> labels;
	public String state;
	public String assignee;
	public String milestone;
	public String comments;
	public String created_at;
	public String updated_at;
	public String closed_at;
	private String message;
	private String rawIssue;
	private int number;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComments_url() {
		return comments_url;
	}

	public void setComments_url(String comments_url) {
		this.comments_url = comments_url;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRawIssue() {
		return rawIssue;
	}

	public void setRawIssue(String rawIssue) {
		this.rawIssue = rawIssue;
	}

	public ArrayList<IssueComment> getComment() {
		return comment;
	}

	public void setComment(ArrayList<IssueComment> comment) {
		this.comment = comment;
	}

	public String getRawComment() {
		return rawComment;
	}

	public void setRawComment(String rawComment) {
		this.rawComment = rawComment;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
