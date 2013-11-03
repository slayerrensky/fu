package fu.netzsys.ex32.three;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fu.netzsys.ex3.one.RecommenderSystem;
import fu.netzsys.ex3.one.SimilarUser;
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
     * @see HttpServlet#HttpServlet()
     */
    public Recommender() {
        super();
        // TODO Auto-generated constructor stub
        String pathToDir = new File("data/").getAbsoluteFile() + "/";
		System.out.println(pathToDir);
		r = new RecommenderSystem(pathToDir);
		similarUserList = new ArrayList<SimilarUser>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map parameters = request.getParameterMap();
		String nextDestination = "/ChooseUser.jsp";
		
	    if (parameters.containsKey("switchUser")){
	    	userID = Integer.parseInt(request.getParameter("switchUser"));
	    	similarUserList = r.getMaxSimilarUser(Uuser.list.get(userID), 0.8, 50);
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
