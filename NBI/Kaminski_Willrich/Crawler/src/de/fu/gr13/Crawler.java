package de.fu.gr13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.sandbox.queries.regex.JakartaRegexpCapabilities;
import org.apache.lucene.sandbox.queries.regex.JavaUtilRegexCapabilities;
import org.apache.lucene.sandbox.queries.regex.RegexCapabilities;
import org.apache.lucene.sandbox.queries.regex.RegexCapabilities.RegexMatcher;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * Servlet implementation class Crawler
 */
@WebServlet("/Crawler")
public class Crawler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int depth = 2;
	ArrayList<URL> urlList = new ArrayList<URL>();
	StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
	Directory index = new RAMDirectory();
	IndexWriterConfig config = null;
	IndexWriter w = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Crawler() {
		super();
		config = new IndexWriterConfig(Version.LUCENE_45, analyzer);
		try {
			w = new IndexWriter(index, config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		String url_str = "not set yet";
		if (request.getParameterValues("url") != null) {
			url_str = request.getParameterValues("url")[0];
			URL url = new URL(url_str);
			crawl(url);
		}

		writer.println("<html>");
		writer.println("<head><title>Hello World Servlet</title></head>");
		writer.println("<body>");
		writer.println("<h1>Hello World from a Servlet!</h1>");
		writer.println("<p> request " + url_str + "</p>");
		writer.println("<form action=\"Crawler\">"
				+ "<p>URL:<br><input name=\"url\" type=\"text\" size=\"100\" maxlength=\"200\" value=\"http://www.udacity.com/cs101x/index.html\">"
				+ "<input type=\"submit\" value=\" Indezieren \">"
				+ "</p></form>");

		for (int i = 0; i < urlList.size(); i++) {
			writer.println(urlList.get(i).toString() + "<br>");
		}

		writer.println("<body>");
		writer.println("</html>");
		writer.close();

		w.commit();
		
		// Test

		System.out.println("Searching for '" + "" + "'");
		Directory directory = w.getDirectory();
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		QueryParser queryParser = new QueryParser(Version.LUCENE_45, "content", analyzer);
		RegexpQuery query = null;
		try {
			query = new RegexpQuery(new Term("content","kick"));
//			query = queryParser.parse(".*kick.*");
		} catch (Exception e) {
			e.printStackTrace();
		}
		TopDocs hits = indexSearcher.search(query,10);
		System.out.println("Number of hits: " + hits.scoreDocs.length);
		
		 ScoreDoc[] scoreDocs = hits.scoreDocs;
		 IndexReader reader = IndexReader.open(index);
		
		 IndexSearcher searcher = new IndexSearcher(reader);
		 for(int i=0;i<scoreDocs.length;++i) {
			    int docId = scoreDocs[i].doc;
			    Document d = searcher.doc(docId);
			    System.out.println((i + 1) + ". " + d.get("content"));
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	private void crawl(URL url) {
		try {
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));

			StringBuilder webpage = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				webpage.append(inputLine);

			// Wortreduzierung
			String replaceAll = webpage.toString().replaceAll("<[^>]*>", "")
					.replaceAll("[!.,-_\"]", "");

			// Duplicate entfernen und indezieren
			Scanner scanner = new Scanner(replaceAll);
			HashSet<String> setList = new HashSet<String>();
			Document addDoc = new Document();
			while (scanner.hasNext()) {
				String next = scanner.next();
				if (setList.add(next))
					addDoc.add(new TextField("content", next, Field.Store.YES));
			}
			w.addDocument(addDoc);
			
			//Kontrollausgabe
			IndexableField[] fields = addDoc.getFields("content");
			for (int i = 0; i < fields.length; i++) {
				System.out.println(fields[i].stringValue());
			}
			
			if (this.depth > 0) {
				this.depth--;
				getLinks(webpage.toString());
				this.depth++;
			}
			in.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void getLinks(String webpage) {

		String linktag = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
		Pattern pattern = Pattern.compile(linktag);
		Matcher matcher = pattern.matcher(webpage);

		while (matcher.find()) {
			String[] urlParts = matcher.group().split("\"|\'");
			if (urlParts.length < 2)
				continue;

			String spezURL = urlParts[1];

			// Normalisiere Link
			spezURL = normalizeLinks(spezURL);

			// PrŸfe auf korrekten Typen
			if (!isCorrectTypeOfLink(spezURL))
				continue;

			URL url = null;
			try {
				url = new URL(spezURL);
			} catch (MalformedURLException e1) {
				// e1.printStackTrace();
				System.out.println(e1.getMessage());
				continue;
			}

			boolean visited = false;
			for (int i = 0; i < urlList.size(); i++) {
				if (urlList.get(i).toString().equals(url.toString()))
					visited = true;
			}
			if (visited)
				continue;
			urlList.add(url);
			crawl(url);
		}
	}

	private String normalizeLinks(String spezURL) {
		return spezURL;
		// TODO:
		/*
		 * HTTP://www.UIOWA.edu -> http://www.uiowa.edu.
		 * http://myspiders.biz.uiowa.edu/faq.html# ->
		 * http://myspiders.biz.uiowa.edu/faq.html
		 * http://dollar.biz.uiowa.edu/%7Epant/ ->
		 */
	}

	private boolean isCorrectTypeOfLink(String spezURL) {
		if (spezURL.endsWith(".html"))
			return true;
		return false;
		/*
		 * media (jpg, pdf ...)
		 */
	}
}
