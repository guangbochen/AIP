package com.guangbo.chen.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 * this class handles index request and forwards relevant responses
 */
public class IndexServlet extends HttpServlet 
{

    /**
     * this method calls doPost method
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * this method handles post requests
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
	}

}
