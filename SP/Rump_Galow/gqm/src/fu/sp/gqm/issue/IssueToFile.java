package fu.sp.gqm.issue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IssueToFile {
	String path;
	
	public IssueToFile(String path) {
		this.path = path;
		CreateFolder(path);
		CreateFolder(path + "/Issue");
		CreateFolder(path + "/Comment");
		
	}
	
	public void CreateFolder(String path)
	{
		File f=new File(path);  
		 // if the directory does not exist, create it
		if (!f.exists()) {
			boolean result = f.mkdir();  
			if(result) {    
				System.out.println("DIR created");  
			}
		}
	}
	
	public int writeToIssueFile(String filename, String content)
	{
	    try {
	      File file = new File(path + "/Issue/" + filename);
	      BufferedWriter output = new BufferedWriter(new FileWriter(file));
	      output.write(content);
	      output.close();
	      return 0;
	    } catch ( IOException e ) {
	       e.printStackTrace();
	       return 1;
	    }
	}
	
	public int writeToCommentFile(String filename, String content)
	{
	    try {
	      File file = new File(path + "/Comment/" +filename);
	      BufferedWriter output = new BufferedWriter(new FileWriter(file));
	      output.write(content);
	      output.close();
	      return 0;
	    } catch ( IOException e ) {
	       e.printStackTrace();
	       return 1;
	    }
	}
	
	public boolean IssueFileExist(String filename)
	{
		File f=new File(path + "/Issue/" + filename);
		if (f.exists())
			return true;
		else
			return false;
		
	}
	
	public String readFromComment(int Comment)
	{
		   String s = path + "/Comment/Comment" + Comment + ".json";
		   return readFrom(s);
	}
	
	public String readFromIssue(int Issue)
	{
		   String s = path + "/Issue/Issue" + Issue + ".json";
		   return readFrom(s);
	}
	
	private String readFrom(String filename)
	{
		   String content = null;
		   File file = new File(filename);
		   try {
		       FileReader reader = new FileReader(file);
		       char[] chars = new char[(int) file.length()];
		       reader.read(chars);
		       content = new String(chars);
		       reader.close();
		   } catch (IOException e) {
		       e.printStackTrace();
		   }
		   return content;
	}
}
