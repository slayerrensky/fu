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
		String search = request.getParameter("search");
		String query = request.getParameter("query");
		Map parameters = request.getParameterMap();
	    System.out.println("search=" + search);
	    System.out.println("query=" + query);
		if (query != null && search.equals("crawl")){
	    	
	    	destSite = query;

	    	System.out.println("Begin to crawl: " + destSite);
	    	request.setAttribute("desti",request.getParameter("query"));
	    	
	    	//ArrayList<String> linkList = c.crawlAll(destSite, 2);
	    	ArrayList<String> linkList = c.crawl(destSite, 0, 1);
	    	
	    	PageLinks pages = new PageLinks();
	    	pages.addAll(c.pages);
	    	PageRank pr = new PageRank(pages);
	    	pr.setAlpha(0.85);
	    	pr.setEpsilon(0.014);
	    	pr.calculate(20);
	    	
	    	Normalizer n = new Normalizer();
	    	for (String link : linkList) {
				URLInformation siteInfo = n.normalize(ContentByURL.getSiteContent(link), link);
				siteInfo.setURL(link);
				indexer.addToIndex(siteInfo);
			}
	    	System.out.println("crawler + indexer = fertig");
	    	request.setAttribute("destination",linkList);
	    }
	    if(parameters.containsKey("search") && 
	    		(request.getParameter("search") == "search") && 
	    		parameters.containsKey("query"))
	    {
	    	LSearch lsearch = new LSearch();	    	
	    	LinkedList<URLInformation> founds = lsearch.SearchForContent(query);
	    	String ul = "<ul>\n";
	    	for (URLInformation ulist : founds) {
				ul += "<li><a href=\"" + ulist.getURL() + "\">" + ulist.getURL() + "</a>" + 
					  "<p>" + ulist.getTitle() +"</p></li>"+"\n";
			}
	    	ul +="</ul>"+"\n";
	    	request.setAttribute("linkliste",ul);
	    }
	    if(parameters.containsKey("pagerank") && 
	    		(request.getParameter("pagerank").equals("test")))
	    {
	    	PageLinks tmpPages = new PageLinks();
	    	Page tmpPage = new Page("A");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("B");
	    	tmpPage.addOutLinkedPage("C");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("C");
	    	tmpPage.addOutLinkedPage("B");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("D");
	    	tmpPage.addOutLinkedPage("A");
	    	tmpPage.addOutLinkedPage("B");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("E");
	    	tmpPage.addOutLinkedPage("B");
	    	tmpPage.addOutLinkedPage("D");
	    	tmpPage.addOutLinkedPage("F");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("F");
	    	tmpPage.addOutLinkedPage("B");
	    	tmpPage.addOutLinkedPage("E");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("G");
	    	tmpPage.addOutLinkedPage("B");
	    	tmpPage.addOutLinkedPage("E");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("H");
	    	tmpPage.addOutLinkedPage("B");
	    	tmpPage.addOutLinkedPage("E");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("I");
	    	tmpPage.addOutLinkedPage("B");
	    	tmpPage.addOutLinkedPage("E");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("J");
	    	tmpPage.addOutLinkedPage("E");
	    	tmpPages.addPage(tmpPage);
	    	
	    	tmpPage = new Page("K");
	    	tmpPage.addOutLinkedPage("E");
	    	tmpPages.addPage(tmpPage);
	    	
	    	PageRank pr = new PageRank(tmpPages);
	    	pr.setAlpha(0.85);
	    	pr.setEpsilon(0.014);
	    	//pr.calculateAllPageRanks();
	    	pr.calculate(35);
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
