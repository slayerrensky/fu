package nbi.ex9.reviewReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class main {

	public static void main(String[] args) throws IOException {
		
		String path = System.getProperty("user.dir") + "/data/";
		RatingList r = new RatingList();
		r.addAll(readall(path+"Canon G3.txt"));
		r.addAll(readall(path+"Canon PowerShot SD500.txt"));
		r.addAll(readall(path+"Canon S100.txt"));
		r.addAll(readall(path+"Nikon coolpix 4300.txt"));
		System.out.println("Anzahl key words: "+r.ratings.size());
		Collections.sort(r.ratings);
		for (RatingWithCounter rating : r.ratings) {
			System.out.println(String.format("[%3d] %s", rating.count, rating.rating.name));
		}
	}
	
	public static ArrayList<Rating> readall(String path) throws IOException{
		ArrayList<Rating> parts = new ArrayList<Rating>();
		FileInputStream fstream = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  parts.addAll(extractInfo(strLine));
		}

//		for (String string : parts) {
//			System.out.println(string);
//		}
		//Close the input stream
		br.close();
		
		return parts;
	}
	
	public static ArrayList<Rating> extractInfo(String line){
		ArrayList<Rating> parts = new ArrayList<Rating>();
		int pos = -1;
		while((pos = line.indexOf("[")) > 0){
			if( pos > 0){
				if(!line.startsWith("#")) parts.add(createRating(cleanStartStr(line.substring(0,pos+4))));
				line = line.substring(pos+4);
				if(line.startsWith("[u]")){
					//[u] : feature not appeared in the sentence.
					line = line.substring(4);
				}
				if(line.startsWith("[p]")){
					//[p] : feature not appeared in the sentence. Pronoun resolution is needed.
					line = line.substring(4);
				}
				if(line.startsWith("[s]")){
					//[s] : suggestion or recommendation.
					line = line.substring(4);
				}
				if(line.startsWith("[cc]")){
					//[cc]: comparison with a competing product from a different brand.
					line = line.substring(5);
				}
				if(line.startsWith("[cs]")){
					//[cs]: comparison with a competing product from the same brand.
					line = line.substring(5);
				}
			}
		}
//		if(parts.size()>0){
//			System.out.println(parts.toString());
//		}
		return parts;
	}

	public static String cleanStartStr(String entry){
		if(entry.charAt(0)==','){
			entry = entry.substring(1);
		}
		
		if(entry.charAt(0)==' '){
			entry = entry.substring(1);
		}
		
		if(entry.charAt(0)==' '){
			entry = entry.substring(1);
		}
		return entry;
	}
	
	public static Rating createRating(String entry){
		String name;
		String rating;
		int pos = entry.indexOf('[');
		name = entry.substring(0, pos);
		rating = entry.substring(pos+1, pos+3);
		if(rating.charAt(1)==']'){
			rating = "+"+rating.charAt(0);
		}
		return new Rating(name, rating);
	}
	
	public static class Rating {
		public String name;
		public Integer rating;
		
		public Rating(String name, String rating) {
			this.name = name;
			this.rating = Integer.parseInt(rating);			
		}
	}
	
	public static class RatingList{
		public ArrayList<RatingWithCounter> ratings;
		
		public RatingList() {
			ratings = new ArrayList<RatingWithCounter>();			
		}
		
		public void addRating(Rating rating){
			boolean isInside = false;
			for (RatingWithCounter list_rating : ratings) {
				if(rating.name.equals(list_rating.rating.name)){
					isInside = true;
					list_rating.count++;
				}
			}
			if(!isInside) ratings.add(new RatingWithCounter(rating));
		}
		
		public void addAll(ArrayList<Rating> ratings){
			for (Rating rating : ratings) {
				addRating(rating);
			}
		}
	}
	
	public static class RatingWithCounter implements Comparable<RatingWithCounter>{
		Rating rating;
		int count;
		public RatingWithCounter(Rating rating){
			this.rating = rating;
			count = 1;
		}
		@Override
		public int compareTo(RatingWithCounter o) {
			if (this.count < o.count)
				return 1;
			else if (this.count > o.count)
				return -1;
			else
				return 0;
		}
	}
}
