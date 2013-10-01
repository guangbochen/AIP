package com.guangbo.chen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
	private double grandTotal;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ArrayList<Orderline> orderList= new ArrayList<Orderline>();
		HttpSession sess = request.getSession();
		orderList = (ArrayList<Orderline>) sess.getAttribute("shoppingCart");
		
        //check action from orders page
		if(request.getParameter("action") != null)
		{
			if(request.getParameter("action").equals("update"))
			{
				//update product quantity
				updateQuantity(request, orderList);
			}
			else if(request.getParameter("action").equals("delete"))
			{
				//remove delete order
				removeOrder(request, orderList);
			}
			else if(request.getParameter("action").equals("cancel"))
			{
				//cancel the entire order
				sess.setAttribute("shoppingCart", null);
				orderList = null;
			}
			else if(request.getParameter("action").equals("purchase"))
			{
				//purchase the order
				purchaseOrder(request, response, orderList);
			}
		}
		
		//update the grand Total
		double grandTotal = calGrandTotal(orderList);
		request.setAttribute("grandTotal", grandTotal);
        request.setAttribute("shoppingCart", orderList);
        
		//forward request to the orders page
        RequestDispatcher view = request.getRequestDispatcher("orders.jsp");
        view.forward(request, response);
		
	}
	
	/**
	 * this method updates product quantity upon the user input
	 * @param request, http request
	 * @param ops, ArrayList contains ordered products
	 */
	private void updateQuantity(HttpServletRequest request, ArrayList<Orderline> orderList)
	{
		try
		{
			int pid = Integer.parseInt(request.getParameter("pid"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			for(Orderline ol : orderList)
			{
				//update quantity & line total of the order upon product id
				if(ol.getProduct().getId() == pid && quantity >0)
				{
					double price = ol.getProduct().getPrice();
					ol.setQuantity(quantity);
					ol.setLineTotal(price*quantity);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//update the session 
		HttpSession sess = request.getSession();
		sess.setAttribute("shoppingCart", orderList);
	}
	
	/**
	 * this method remove order upon the user request
	 * @param request, http request
	 * @param ops, ArrayList contains ordered products
	 */
	private synchronized void removeOrder(HttpServletRequest request,ArrayList<Orderline> orderList)
	{
		try
		{
			int pid = Integer.parseInt(request.getParameter("pid"));
			for (Iterator<Orderline> it = orderList.iterator(); it.hasNext(); )
			{
				Orderline op = (Orderline) it.next();  
				if(op.getProduct().getId() == pid )
				{
					it.remove();
				}
			}
			//update the shopping cart session 
			HttpSession sess = request.getSession();
			sess.setAttribute("shoppingCart", orderList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * this method validate purchasing order action, if is not empty then purchasing the order
	 * @param request
	 * @param response
	 * @param ops
	 */
	private void purchaseOrder(HttpServletRequest request, HttpServletResponse response, 
			ArrayList<Orderline> orderList)
	{
		if(orderList == null || orderList.isEmpty())
		{
			//set empty order message
			request.setAttribute("emptyOrder", "Please select at least one product to purchase.");
		}
		else
		{
	        RequestDispatcher view = request.getRequestDispatcher("/purchase");
	        try {
				view.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * this method calculates the grand total price of the order list
	 * @param orderList, Arraylist of orderline
	 * @return double grandTotal
	 */
	private double calGrandTotal(ArrayList<Orderline> orderList) {
		double grandTotal = 0.00;
		if(orderList != null)
		{
			for(Orderline ol : orderList)
			{
				grandTotal += ol.getLineTotal();
				grandTotal = Math.round(grandTotal*100.00)/100.00;
			}
		}
		return grandTotal;
	}

}
