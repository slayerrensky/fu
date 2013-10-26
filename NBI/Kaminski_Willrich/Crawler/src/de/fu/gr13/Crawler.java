package de.fu.gr13;

import java.io.BufferedReader;
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

/**
 * Servlet implementation class Crawler
 */
@WebServlet("/Crawler")
public class Crawler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int depth = 2;
	ArrayList<URL> urlList = new ArrayList<URL>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Crawler() {
		super();
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
		
		for (int i = 0;i<urlList.size();i++){
			writer.println(urlList.get(i).toString() + "<br>");
		}
		
		writer.println("<body>");
		writer.println("</html>");
		writer.close();

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
			if (!spezURL.endsWith(".html"))
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
}
