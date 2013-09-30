package com.guangbo.chen.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guangbo.chen.ejb.OrderDAO;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

/**
 * Servlet implementation class PurchaseServlet
 * this class handles HTTP requests from purchase page
 */
public class PurchaseServlet extends HttpServlet 
{
	@EJB (name="OrderEjb",mappedName="ejb/order")
	private OrderDAO odao; 

	/**
	 * this method calls doPost method
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doPost(request,response);
	}

	/**
	 * this method handles request from purchase page
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
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
		        RequestDispatcher view = request.getRequestDispatcher("orders");
		        view.forward(request, response);
			}
			else if(request.getParameter("action").equals("process"))
			{
				//proceed the order
				proceedOrder(request,response);
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
			Order order = new Order(title,surname,givenName,email,
					unit,street,state,suburb,postcode,country,payment);
			boolean validation = order.formValidation(title,surname,givenName,email,
					unit,street,state,suburb,postcode,country,payment);
			//if the form has empty input
			if(validation == false)
			{
				request.setAttribute("order", order);
				request.setAttribute("isempty", " Can't be empty");
			}
			else
			{
				//forward to confirms purchasing order
				request.getSession().setAttribute("order", order);
		        RequestDispatcher view = request.getRequestDispatcher("confirmPurchase.jsp");
		        view.forward(request, response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * this method process the order and saves order into the database
	 * @param request
	 * @param response
	 * @param order
	 */
	private void proceedOrder(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			//get shopping cart details
			HttpSession sess = request.getSession();
			Order order = (Order) request.getSession().getAttribute("order");
			ArrayList<Orderline> orderList = (ArrayList<Orderline>)sess.getAttribute("shoppingCart");
			if(order != null && orderList != null)
			{
				//save customer order to the database
				odao.addOrder(orderList, order);
	        	request.setAttribute("orderNumber", odao.getUniqueOrderNum());
	        	//if saved into database successfully 
	        	//forward to notice page and clean the HTTP session
	        	sess.setAttribute("shoppingCart", null);
	        	sess.setAttribute("order", null);
		        RequestDispatcher view = request.getRequestDispatcher("purchaseNotice.jsp");
				view.forward(request, response);
			}	
			else
			{
				//forward to error page if repeat the purchase action with empty order
		        RequestDispatcher view = request.getRequestDispatcher("error.jsp");
		        view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
