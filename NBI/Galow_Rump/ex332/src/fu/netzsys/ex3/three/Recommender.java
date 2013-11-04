package fu.netzsys.ex3.three;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.URL;

import fu.netzsys.ex3.one.RecommenderSystem;
import fu.netzsys.ex3.one.SimilarUser;
import fu.netzsys.ex3.one.Udata;
import fu.netzsys.ex3.one.Uuser;

/**
 * Servlet implementation class Recommender
 */
@WebServlet("/Recommender")
public class Recommender extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int userID = 0;
	private RecommenderSystem r;
	private ArrayList<SimilarUser> similarUserList;
       
    /**
     * @throws MalformedURLException 
     * @see HttpServlet#HttpServlet()
     */
    public Recommender() throws MalformedURLException {
        super();
    }
    
    public void init() throws ServletException{
    	System.out.println("loading data from path: " + getServletContext().getRealPath("/WEB-INF/data"));
    	r = new RecommenderSystem(getServletContext().getRealPath("/WEB-INF/data") + "/");
    	similarUserList = new ArrayList<SimilarUser>();
    	System.out.println("Files loaded.");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map parameters = request.getParameterMap();
		String nextDestination = "/ChooseUser.jsp";
		
	    if (parameters.containsKey("switchUser")){
	    	try{
	    		userID = Integer.parseInt(request.getParameter("switchUser"));
	    	}catch(Exception e){
	    		userID = -1;
	    	}
	    	request.setAttribute("userid",userID);
	    	if((userID >= 0) && (Uuser.list.size() > userID)){
		    	similarUserList = r.getMaxSimilarUser(Uuser.list.get(userID), 0.8, 50);
		    	request.setAttribute("myRatings",r.getRatedData(Uuser.list.get(userID)));
	    	}
	    	nextDestination = "/ChooseUser.jsp";
	    }
		
	    RequestDispatcher view = request.getRequestDispatcher(nextDestination);
	    view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
