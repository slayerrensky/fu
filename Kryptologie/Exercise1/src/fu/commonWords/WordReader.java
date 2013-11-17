package fu.commonWords;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WordReader {
	private String path;
	private ArrayList<CommonWord> list = new ArrayList<CommonWord>(); 
	
	public WordReader(String path)
	{
		this.path = path;
		
	}
	/**
	 * rank  wordform         abs     r        mod
	 * 1        the         225300    29   223066.9
	 * @param path
	 */
	public ArrayList<CommonWord> readFile(String path)
	{
		try{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream(path);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			br.readLine();
			br.readLine();
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				ArrayList<String> WithoutSpaceStr = new ArrayList<String>();
				String[] splitStr = strLine.split(" ");
				for (String s : splitStr) {
					if (s.length()>0){
						WithoutSpaceStr.add(s);
					}
				}
				if (WithoutSpaceStr.get(1).length() > 4)
				list.add(new CommonWord(WithoutSpaceStr.get(1).toUpperCase(),
						Integer.parseInt(WithoutSpaceStr.get(2)), 
						Integer.parseInt(WithoutSpaceStr.get(0))));
			}
			//Close the input stream
			in.close();
		} catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public ArrayList<CommonWord> readFile()
	{
		return readFile(path);
	}
	
	public void addWord(CommonWord w)
	{
		list.add(w);
	}
	
}
