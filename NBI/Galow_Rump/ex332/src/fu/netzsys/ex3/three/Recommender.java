package fu.netzsys.ex3.three;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import fu.netzsys.ex3.one.RelevantRatedItemWeigth;
import fu.netzsys.ex3.one.SimilarUser;
import fu.netzsys.ex3.one.Udata;
import fu.netzsys.ex3.one.Uuser;
import fu.netzsys.ex3.one.Uitem;

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
		request.setAttribute("userid",userID);
		if (request.getContextPath().contains("MovieList.jsp"))
		{
			System.out.println("open Movie list");
		}
		
	    if (parameters.containsKey("switchUser")){
	    	try{
	    		userID = Integer.parseInt(request.getParameter("switchUser"));
	    	}catch(Exception e){
	    		userID = -1;
	    	}
	    	request.setAttribute("userid",userID);
	    	request.setAttribute("sex",Uuser.list.get(userID).getMale());
	    	request.setAttribute("age",Uuser.list.get(userID).getAge());
	    	request.setAttribute("zipcode",Uuser.list.get(userID).getZipCode());
	    	request.setAttribute("occupations",Uuser.list.get(userID).getOccupation().toString());
	    	
	    	if((userID >= 0) && (Uuser.list.size() > userID)){
		    	similarUserList = r.getMaxSimilarUser(Uuser.list.get(userID), 0.8, 50);
		    	request.setAttribute("myRatings",r.getRatedData(Uuser.list.get(userID)));
		    	String page = request.getRequestURL().toString();
		    	String list = "<tr><th>movie</th><th>my rating</th></tr>";
		    	for (Udata d :r.getRatedData(Uuser.list.get(userID)))
		    	{
		    		String href = "href=\"" + page + "?movie=" + d.getItem().getId() + "\""; 
		    		list += "<tr><th><a "+ href + ">" + d.getItem().getTitle() + "</a></th><th>" + d.getRating() + "</th></tr>\n";
		    	}
		    	request.setAttribute("list",list);
	    	}

	    	nextDestination = "/ChooseUser.jsp";
	    }
		
	    if (parameters.containsKey("movies")){
	    	ArrayList<Uitem> movies = r.getMovielist();
	    	String page = request.getRequestURL().toString();
	    	String list = new String();
	    	for (Uitem m :movies)
	    	{
	    		String href = "href=\"" + page + "?movie=" + m.getId() + "\""; 
	    		list += "<li><a "+ href + ">" + m.getTitle() + "</a></li>\n";
	    	}
	    	request.setAttribute("list",list);
	    	nextDestination = "/MovieList.jsp";
	    }else if (parameters.containsKey("movie")){
	    	int movieID = -1;
	    	try{
	    		movieID = Integer.parseInt(request.getParameter("movie"));
	    	}catch(Exception e){
	    		movieID = -1;
	    	}
	    	Uitem item = Uitem.getItemByID(movieID);
	    	Udata data = r.getDataRating(item, Uuser.getUserByID(userID));
	    	String MovieInformations = new String();
	    	MovieInformations += "<h2>" + item.getTitle() +"</h2>\n";
	    	MovieInformations += "<p>Title: "+item.getTitle() +"</p>\n";
	    	MovieInformations += "<p>Datum: "+item.getDate() +"</p>\n";
	    	MovieInformations += "<p><a href\""+item.getLink()+"\">" + "more Information about this movie" + "</p>\n";
	    	if (data == null){
	    		//Date Formular
	    		MovieInformations += "<p>You Rate the move: not rated</p>";
	    	}
	    	else
	    	{
	    		Date date = new Date(data.getUnixTimestamp());
	    		SimpleDateFormat dateformater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		MovieInformations += "<p>You Rate the move: "+data.getRating() + "at" + dateformater.format(date) + "</p>\n";
	    	}
	    	request.setAttribute("MovieID",movieID);
	    	request.setAttribute("MovieInformations",MovieInformations);
	    	nextDestination = "/Movie.jsp";
	    }else if(parameters.containsKey("predict")){
	    	ArrayList<RelevantRatedItemWeigth> tmp =  r.getRelevantItems(Uuser.list.get(userID), similarUserList);
	    	String page = request.getRequestURL().toString();
	    	String list = "<tr><th>movie</th><th>predicted rating<tr></tr>";
			for (RelevantRatedItemWeigth ruitem : tmp) {
				String href = "href=\"" + page + "?movie=" + ruitem.item.getId() + "\"";
	    		//list += "<li><a "+ href + ">" + ruitem.item.getTitle() + " Rating: " + String.format("%.1f", ruitem.rating.doubleValue()) + "</a></li>\n";
				list += "<tr><th><a "+ href + ">" + ruitem.item.getTitle() + "</a></th><th>" + String.format("%.1f", ruitem.rating.doubleValue()) + "</th></tr>\n";
			}
			request.setAttribute("list",list);
			nextDestination = "/PredictedList.jsp";
	    }
	    
	    RequestDispatcher view = request.getRequestDispatcher(nextDestination);
	    view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post anfrage");
	}

}
