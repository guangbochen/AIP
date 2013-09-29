package com.guangbo.chen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RespectBinding;

import com.guangbo.chen.ejb.ProductDAO;
import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;
import com.sun.tools.javac.util.List;

public class ProductServlet extends HttpServlet {
	@EJB (name="productEjb",mappedName="ejb/product")
	private ProductDAO pdao; 
	private final static int recordsPerPage = 8;
	private int page;
	private int noOfRecords;
	private int noOfPages;
	private ArrayList<Product> productList;
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//initialize variables
		productList = null;
		page =1;
		noOfRecords = 0;
		noOfPages = 0;
		
		//if HTTP request contains page parameter
		if(request.getParameter("page") != null) {
			try 
			{
				page = Integer.parseInt(request.getParameter("page"));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//if HTTP request contains action
		if(request.getParameter("action") != null)
		{
			if(request.getParameter("action").equals("category"))
			{
				//find a list of products upon the category
				findAllProductsByCategory(request,response);
			}
			else if(request.getParameter("action").equals("order"))
			{
				//add customer order to the shopping cart and forward to the order page
				addOrderToShopingCart(request);
		        RequestDispatcher view = request.getRequestDispatcher("/orders");
		        view.forward(request, response);
			}
		}
		else
		{
			//displays all of the products
			noOfRecords = pdao.findAll().size();
			productList = (ArrayList<Product>) pdao.findAllByPagination((page-1)*recordsPerPage,recordsPerPage);
		}
		
		// calculates the total number of pages for paging
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		//get a list product categories for category filter
		ArrayList<String> cateList = (ArrayList<String>) pdao.findAllCategory();
		
		//forward list of product to the products page
		request.setAttribute("productList", productList);
        request.setAttribute("cateList", cateList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("products.jsp");
        view.forward(request, response);
	}
	

	/**
	 * this method find a list of product by category in paging
	 * @param request, servlet request
	 * @param response, servlet response
	 */
	private void findAllProductsByCategory(HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("category");
		if(category.equals("All"))
		{
			noOfRecords = pdao.findAll().size();
			productList = (ArrayList<Product>) pdao.findAllByPagination((page-1)*recordsPerPage,recordsPerPage);
		}
		else
		{
			noOfRecords = pdao.findAllByCategory(category).size();
			productList = (ArrayList<Product>) pdao.findAllCategoryByPagination(category,(page-1)*recordsPerPage,recordsPerPage);
			//return list of products upon category
	        request.setAttribute("category", category);
		}
	}
	
	/**
	 * this method add selected product to the shopping cart/updates my orders
	 * @param request, http servlet request
	 */
	private void addOrderToShopingCart(HttpServletRequest request)
	{
		try
		{
			HttpSession sess = request.getSession();
			ArrayList<Orderline> orderList = (ArrayList<Orderline>) sess.getAttribute("shoppingCart");
			int pid = Integer.parseInt(request.getParameter("id"));
			String category = request.getParameter("category");
			String code = request.getParameter("code");
			String description  = request.getParameter("description");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			
			//if the order session does not exist create new http session for orders
			if(sess.isNew() || orderList == null)
			{
				orderList = new ArrayList<Orderline>();
				Product p = new Product(pid,category, code, description, price);
				Orderline ol = new Orderline(p,quantity,price*quantity);
				orderList.add(ol);
			}
			else
			{
				boolean newOrder = true;
				//update orderline quantity if product is already exist
				for(Orderline ol : orderList)
				{
					if(ol.getProduct().getId() == pid)
					{
						//update the total quantity 
						int totalQuantity = ol.getQuantity() + quantity;
						ol.setQuantity(totalQuantity);
						//update the line total
						ol.setLineTotal(price*ol.getQuantity());
						newOrder = false;
					}		
				}
				//if is a new order then add it to the orderList
				if(newOrder)
				{
					
					Product p = new Product(pid,category, code, description, price);
					Orderline ol = new Orderline(p,quantity,price*quantity);
					orderList.add(ol);
				}
			}
			//update the HTTP shoppingCart session 
			sess.setAttribute("shoppingCart", orderList);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
