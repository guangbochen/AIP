package com.guangbo.chen.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guangbo.chen.ejb.OrderDAO;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

/**
 * Servlet implementation class ViewOrderServlet
 */
public class ViewOrderServlet extends HttpServlet {
	@EJB (name="OrderEjb",mappedName="ejb/order")
	private OrderDAO odao; 

    /**
     * this method calls dopost request
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * this method handles request from viewOrder page and forward response to it
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //if has http request
		if(request.getParameter("action") != null) {
			
			if(request.getParameter("action").equals("check")) {
				searchingOrders(request,response);
			}
		}
		
        RequestDispatcher view = request.getRequestDispatcher("viewOrder.jsp");
        view.forward(request, response);
	}
	
	/**
	 * this method validates the searching form
	 */
	private void searchingOrders(HttpServletRequest request, HttpServletResponse response) {
		String number = request.getParameter("orderNumber");
		String name = request.getParameter("surname");
		if(number.equals("") || name.equals(""))
		{
			request.setAttribute("emptyNum", "empty order number");
			request.setAttribute("emptyName", "empty surname");
			request.setAttribute("error", "has-error");
			//save user input to avoid repeating 
			request.setAttribute("number", number);
			request.setAttribute("name", name);
		}
		else
		{
			Order order = odao.findOrderByOrderNumAndSurname(number, name);
			List<Orderline> ols = (List<Orderline>) order.getOrderLines();
			double grandTotal = odao.getGrandTotal(ols);
			request.setAttribute("order", order);
			request.setAttribute("orderlines", ols);
			request.setAttribute("grandTotal", grandTotal);
		}
		
	}

}
