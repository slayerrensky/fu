package de.fu.gr13;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.sandbox.queries.regex.RegexQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * Servlet implementation class Crawler
 */
@WebServlet("/Searcher")
public class Searcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int depth = 2;
	ArrayList<URL> urlList = new ArrayList<URL>();
	StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_45);
	Directory index; 
	IndexWriterConfig config = null;
	IndexWriter w = null;
	String indexFile = "path/Library/Tomcat/webapps/[WBI-1314]-exercise21-group13/index";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Searcher() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		config = new IndexWriterConfig(Version.LUCENE_45, analyzer);
		urlList = new ArrayList<URL>();
		try {
			index = FSDirectory.open(new File(indexFile));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			w = new IndexWriter(index, config);
		} catch (IOException e) {
			e.printStackTrace();
		}

		PrintWriter writer = response.getWriter();

		String searchString = "not set yet";
		if (request.getParameterValues("search") != null) {
			searchString = request.getParameterValues("search")[0];
		}
		
		writer.println("<html>");
		writer.println("<head><title>Searcher</title></head>");
		writer.println("<body>");
		writer.println("<h1>Searcher by Lars Willrich, Peter Kaminski</h1>");
		writer.println("<p> request " + searchString + "</p>");
		writer.println("<form action=\"\">"
				+ "<p>URL:<br><input name=\"search\" type=\"text\" size=\"100\" maxlength=\"200\" value=\"kick\">"
				+ "<input type=\"submit\" value=\" Indezieren \">"
				+ "</p></form>");

		System.out.println("Search for " + searchString);
		writer.println("path" + getServletContext().getRealPath ("."));
		new File("");
		ArrayList<Document> search = search(searchString, "content");
		if (search != null)
		for (int i = 0; i<search.size();i++) {
			Document d = (Document) search.get(i);
			
			String title = d.get("title");
			if (title == null) {
				int length = d.get("content").length();
				System.out.println(length);
				if (length > 40) length = 40; 
				title = d.get("content").substring(0, length);
			}
			
			writer.println((i + 1) + ". " + d.get("url") + "\t"
					+ title + "<br>");
		}
		
		System.out.println("End.");
		writer.println("<body>");
		writer.println("</html>");
		writer.close();
		w.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	private ArrayList<Document> search(String string, String field) {
		
		ArrayList<Document> list = new ArrayList<Document>();
		String querystr = string;

		try {
			// regex
			RegexQuery q = new RegexQuery(new Term(field, ".*" + querystr
					+ ".*"));

			int hitsPerPage = 10;
			IndexReader reader = DirectoryReader.open(index);
			IndexSearcher searcher = new IndexSearcher(reader);
			TopScoreDocCollector collector = TopScoreDocCollector.create(
					hitsPerPage, true);
			searcher.search(q, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;

			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				list.add(d);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
