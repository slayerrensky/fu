package fu.netzsys.crawler_lucene;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.util.Version;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;

public class LSearch {

	Analyzer analyzer;
	String indexPath;
	Directory indexDir;

	public LSearch()
	{
		indexPath = System.getProperty("user.dir") + "\\lucene";
		try {
			File test = new File(indexPath);
			System.out.println(test.getAbsoluteFile());
			indexDir = FSDirectory.open(new File(indexPath));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		analyzer = new StandardAnalyzer(Version.LUCENE_45);
	}
	
	public LinkedList<String> SearchForContent(String querystr)
	{
		return SearchFor(querystr,"content");
	}
	
	public LinkedList<String> SearchFor(String querystr, String searchField)
	{
		ScoreDoc[] hits = null;
		IndexSearcher searcher = null;
		int hitsPerPage = 10;
	    IndexReader reader;
		try {
			Query q = new QueryParser(Version.LUCENE_45, "content", analyzer).parse(querystr);
			reader = DirectoryReader.open(indexDir);
			 searcher = new IndexSearcher(reader);
		    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		    searcher.search(q, collector);
		    hits= collector.topDocs().scoreDocs;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (searcher != null && hits !=null)
		{
			System.out.println("Found " + hits.length + " hits.");
			
			LinkedList<String> founds = new LinkedList<String>();
			
			for(int i=0;i<hits.length;++i) {
		       int docId = hits[i].doc;
		       Document d;
		       try {
		    	   d = searcher.doc(docId);
		    	   founds.add(d.get("url"));
		    	   System.out.println((i + 1) + ". " + d.get("url"));
		    	   
		       } catch (IOException e) {
		    	   // TODO Auto-generated catch block
		    	   e.printStackTrace(); 
		       }
			  
		    }
			return founds;
		}
		 return null;
		
	     
	}
	
}