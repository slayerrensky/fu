package de.fu.gr13;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
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
@WebServlet("/Crawler")
public class Crawler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int depth = 1;
	ArrayList<URL> urlList = new ArrayList<URL>();
	StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_45);
	Directory index;
	IndexWriterConfig config = null;
	IndexWriter w = null;
	String indexFile = "/Library/Tomcat/webapps/[WBI-1314]-exercise21-group13/index";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Crawler() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		config = new IndexWriterConfig(Version.LUCENE_45, analyzer)
				.setOpenMode(OpenMode.CREATE);
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

		String url_str = "not set yet";
		if (request.getParameterValues("url") != null) {
			url_str = request.getParameterValues("url")[0];
			URL url = new URL(url_str);
			crawl(url);
		}

		writer.println("<html>");
		writer.println("<head><title>Crawler</title></head>");
		writer.println("<body>");
		writer.println("<h1>Crawler by Lars Willrich, Peter Kaminski</h1>");
		writer.println("<p> request " + url_str + "</p>");
		writer.println("<form action=\"\">" 
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
		w.close();
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

			indicatePage(url, webpage);

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

	private void indicatePage(URL url, StringBuilder webpage) {
		try {
			Document addDoc = new Document();

			// Title
			if (webpage.toString().contains("<title>")) {
				String titleTag = "<title[^>]*>(.*)</title>";
				Pattern pattern = Pattern.compile(titleTag);
				Matcher matcher = pattern.matcher(webpage);
				String title = null;
				if (matcher.find()) {
					title = matcher.group();
					title = title.replaceAll("<title>", "").replaceAll(
							"</title>", "");
				}
				addDoc.add(new TextField("title", title, Field.Store.YES));
			}

			// Content
			String replaceAll = webpage.toString().replaceAll("<[^>]*>", "")
					.replaceAll("[!.,\\u002D_\"]", "");

			addDoc.add(new StringField("url", url.toString(), Field.Store.YES));
			addDoc.add(new TextField("content", replaceAll, Field.Store.YES));

			// Images
			String img = getImg(webpage.toString(), url.toString());
			if (img != null) {
				addDoc.add(new TextField("image", img, Field.Store.YES));
			}

			w.addDocument(addDoc);
		} catch (Exception e) {
			e.printStackTrace();
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

			// Pr�fe auf korrekten Typen
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
			System.out.println(url);
			crawl(url);
		}
	}

	private void search(String string, String field) {
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

			System.out.println("Found " + hits.length + " hits.");
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				System.out.println((i + 1) + ". " + d.get("url") + "\t"
						+ d.get("content"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
		if (spezURL.endsWith(".php"))
			return true;
		return false;
		/*
		 * media (jpg, pdf ...)
		 */
	}

	private String getImg(String str, String url) {

		Pattern pattern = Pattern.compile("<img[^>]*>");
		Matcher matcher = pattern.matcher(str);
		str = null;
		while (matcher.find()) {
			String group = matcher.group();
			Pattern patternsrc = Pattern.compile("src=(\"|')[^(\"|')]*(\"|')");
			Matcher matchersrc = patternsrc.matcher(group);

			if (matchersrc.find())
				group = matchersrc.group();
			str = group.replaceAll("src=", "").replaceAll("'", "");
		}

		return str;
	}
}
