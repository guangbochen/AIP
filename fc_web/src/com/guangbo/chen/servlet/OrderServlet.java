package com.guangbo.chen.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Orderline> orderList= new ArrayList<Orderline>();
		//get ordered product from http session
		HttpSession sess = request.getSession();
		orderList = (ArrayList<Orderline>) sess.getAttribute("shoppingCart");
        //check HTTP servlet request
		if(request.getParameter("action") != null)
		{
			if(request.getParameter("action").equals("update"))
			{
				//update product quantity
//				updateQuantity(request, ops);
			}
			else if(request.getParameter("action").equals("delete"))
			{
				//remove delete order
//				removeOrder(request, ops);
			}
			else if(request.getParameter("action").equals("cancel"))
			{
				//cancel the entire order
//				sess.setAttribute("shoppingCart", null);
//				ops = null;
			}
			else if(request.getParameter("action").equals("purchase"))
			{
				//purchase the order
//				purchaseOrder(request, response, ops);
			}
		}
		
		//update the grand Total
//		if( ops!=null )
//		{
//			OrderedProduct op = new OrderedProduct();
//			double grandTotal = op.getGrandTotal(ops);
//			request.setAttribute("grandTotal", grandTotal);
//		}
//		else
//		{
//			//if has no orders set grandTotal as 0
//			double grandTotal = 0.0;
//			request.setAttribute("grandTotal", grandTotal);
//		}
		
//		Product p = new Product(2,"catege1", "1", "balahbalah", 20.0);
//		Orderline ol = new Orderline(p,2,20.0*2);
//		orderList.add(ol);
        request.setAttribute("shoppingCart", orderList);
        request.setAttribute("test", "test");
		//forward OrderServlet view to the orders jsp page
        RequestDispatcher view = request.getRequestDispatcher("orders.jsp");
        view.forward(request, response);
		
	}

}
