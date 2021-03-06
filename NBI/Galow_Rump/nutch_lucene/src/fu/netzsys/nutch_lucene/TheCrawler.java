package fu.netzsys.crawler_lucene;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TheCrawler
 */
@WebServlet(
		urlPatterns = { "/TheCrawler" }, 
		initParams = { 
				@WebInitParam(name = "toCrawl", value = "", description = "destination for crawling")
		})
public class TheCrawler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Indexer indexer;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheCrawler() {
        super();
        // TODO Auto-generated constructor stub
        indexer = new Indexer();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Crawler c = new Crawler();
		String destSite = "leer";
		Map parameters = request.getParameterMap();
	    if (parameters.containsKey("search") && parameters.containsKey("query")){
	    	System.out.println("request");
	    	String type = request.getParameter("search");
	    	if(type.equals("crawl")){
	    		destSite = request.getParameter("query");
		    	System.out.println("Begin to crawl: " + destSite);
		    	request.setAttribute("desti",request.getParameter("query"));
		    	
		    	//ArrayList<String> linkList = c.crawlAll(destSite, 2);
		    	ArrayList<String> linkList = c.crawl(destSite, 0, 1);
		    	Normalizer n = new Normalizer();
		    	for (String link : linkList) {
					URLInformation siteInfo = n.normalize(ContentByURL.getSiteContent(link),link );
					indexer.addAllToIndex(siteInfo);
				}
		    	System.out.println("crawler + indexer = fertig");
		    	request.setAttribute("destination",linkList);	    		
	    	}else{
	    		LSearch search = new LSearch();
	    		System.out.println("Begin to search");
		    	String query = request.getParameter("query");
		    	String searchtype = "";
		    	if(parameters.containsKey("searchtype")){
		    		searchtype = request.getParameter("searchtype");
		    		if( ! (searchtype.equals("url") || searchtype.equals("img"))){
		    			searchtype = "url";
		    		}
		    	}else{
		    		searchtype = "url";
		    	}
		    	//LinkedList<URLInformation> founds = search.SearchForContent(query);
		    	LinkedList<URLInformation> founds = search.SearchForType(query, searchtype);
		    	String ul = "<ul>\n";
		    	for (URLInformation ulist : founds) {
					ul += "<li><a href=\"" + ulist.getURL() + "\">" + ulist.getURL() + "</a>" + 
						  "<p>" + ulist.getTitle() +"</p></li>"+"\n";
				}
		    	ul +="</ul>"+"\n";
		    	request.setAttribute("linkliste",ul);
	    	}	
	    }
	    	
	    RequestDispatcher view = request.getRequestDispatcher("/showInfo.jsp");
	    view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
