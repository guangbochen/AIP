package com.guangbo.chen.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guangbo.chen.jpa.Order;

/**
 * Servlet implementation class PurchaseServlet
 * this class handles HTTP requests from purchase page
 */
public class PurchaseServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       

	/**
	 * this method calls doPost method
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}

	/**
	 * this method handles request from purchase page
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
        //check action from purchase page
		if(request.getParameter("action") != null)
		{
			if(request.getParameter("action").equals("submit"))
			{
				//submit purchasing order
				validateForm(request, response);
			}
			else if(request.getParameter("action").equals("cancle"))
			{
				//cancel to purchasing the order
		        RequestDispatcher view = request.getRequestDispatcher("/orders");
		        view.forward(request, response);
			}
		}
		
        RequestDispatcher view = request.getRequestDispatcher("purchase.jsp");
        view.forward(request, response);
		
	}
	
	
	/**
	 * this method validates the customer details input
	 * @param request
	 * @param response
	 */
	private void validateForm(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			//get customer details
			String title = request.getParameter("title");
			String surname = request.getParameter("surname");
			String givenName = request.getParameter("givenName");
			String email = request.getParameter("email");
			String unit = request.getParameter("unit");
			String street = request.getParameter("street");
			String state = request.getParameter("state");
			String suburb = request.getParameter("suburb");
			String postcode = request.getParameter("postcode");
			String country = request.getParameter("country");
			String payment = request.getParameter("creditCard");
			Order order = new Order(title,surname,givenName,email,unit,street,state,suburb,postcode,country,payment);
			boolean validation = order.formValidation(title,surname,givenName,email,unit,street,state,suburb,postcode,country,payment);
			//if the form has empty input
			if(validation == false)
			{
				request.setAttribute("order", order);
				request.setAttribute("isempty", "Can't be empty");
			}
			else
			{
				//forward to confirms purchasing order
				request.getSession().setAttribute("order", order);
		        RequestDispatcher view = request.getRequestDispatcher("purchases");
		        view.forward(request, response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
