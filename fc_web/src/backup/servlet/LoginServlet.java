package backup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * this method calls doPost method
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * this method handles login http request
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action") != null)
		{
			if(request.getParameter("action").equals("login"))
			{
				ServletContext userWeb = getServletContext();
				ServletContext adminWeb = userWeb.getContext("/admin");
		        RequestDispatcher view = request.getRequestDispatcher("admin.jsp");
		        view.forward(request, response);
			}
			
		}
		else
		{
	        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
	        view.forward(request, response);
		}
		
	}

}
