package de.fu.gr13;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_45);
	Directory index;
	IndexWriterConfig config = null;
	IndexWriter w = null;
	String indexFile = "/Users/larswillrich/Entwicklung/Projekte/FU_renskyGithub/fu/NBI/Kaminski_Willrich/Crawler/index";

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
		try {
			index = FSDirectory.open(new File(indexFile));
		} catch (IOException e1) {
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

		String type = "html";
		if (request.getParameterValues("type") != null) {
			if (request.getParameterValues("type")[0].equals("image"))
				type = "image";
		}

		writer.println("<html>");
		writer.println("<head><title>Searcher</title></head>");
		writer.println("<body>");
		writer.println("<h1>Searcher by Lars Willrich, Peter Kaminski</h1>");
		writer.println("<p> request " + searchString + "</p>");
		writer.println("<form action=\"Searcher\">"
				+ "<p>URL:<br><input name=\"search\" type=\"text\" size=\"100\" maxlength=\"200\" value=\"\">"
				+ "<input type=\"submit\" value=\" Indezieren \">"
				+ "</p></form>");

		System.out.println("Search for " + searchString);

		if (type.equals("html")) {
			ArrayList<Document> search = search(searchString.toLowerCase(),
					"content");
			ArrayList<String> titles = getStringListFromSearchResult(search,
					"content");

			ArrayList<Document> searchTitle = search(
					searchString.toLowerCase(), "title");
			ArrayList<String> content = getStringListFromSearchResult(
					searchTitle, "content");

			writer.println("Titel<br>");
			writer.println("found " + titles.size() + "<br");
			printListOut(titles, writer);
			writer.println("<br><br>Content<br>");
			writer.println("found " + content.size() + "<br");
			printListOut(content, writer);
		} else if (type.equals("image")) {
			ArrayList<Document> search = search(searchString.toLowerCase(),
					"image");
			ArrayList<String> content = getStringListFromSearchResult(search,
					"image");

			writer.println("<br><br>Content<br>");
			writer.println("found " + search.size() + "<br");
			printListOut(content, writer);
		}

		writer.println("<body>");
		writer.println("</html>");
		writer.close();
		w.close();

	}

	private void printListOut(ArrayList<String> content, PrintWriter writer) {
		for (int i = 0; i < content.size(); i++) {
			writer.println(content.get(i));
		}
	}

	private ArrayList<String> getStringListFromSearchResult(
			ArrayList<Document> search, String type) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < search.size(); i++) {
			Document d = (Document) search.get(i);

			if (type.equals("title")) {
				String title = d.get("title");
				if (title == null) {
					int length = d.get("content").length();
					if (length > 40)
						length = 40;
					title = d.get("content").substring(0, length);
				}
				list.add((i + 1) + ". " + d.get("url") + "\t" + title + "<br>");
			} else if (type.equals("image")) {
				String image = d.get("image");
				list.add((i + 1) + ". " + d.get("url") + "\t" + image + "<br>");
			}

		}
		return list;
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
