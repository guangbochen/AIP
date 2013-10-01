package com.guangbo.chen.product.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.ProductDAO;
import com.guangbo.chen.jpa.Product;

public class ProductsAction implements Action{
	private ProductDAO pdao;
	private final static int recordsPerPage = 8;
	private int page;
	private int noOfRecords;
	private int noOfPages;
	private ArrayList<Product> productList;
	
	public ProductsAction(ProductDAO pdao) {
		this.pdao = pdao;
	}


	public Dispatcher execute(HttpServletRequest request) {
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
		
		//displays all of the products
//		noOfRecords = pdao.findAll().size();
//		productList = (ArrayList<Product>) pdao.findAllByPagination((page-1)*recordsPerPage,recordsPerPage);
//		
//		// calculates the total number of pages for paging
//		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//		//get a list product categories for category filter
//		ArrayList<String> cateList = (ArrayList<String>) pdao.findAllCategory();
//		
//		//forward list of product to the products page
//		request.setAttribute("productList", productList);
//        request.setAttribute("cateList", cateList);
//        request.setAttribute("noOfPages", noOfPages);
//        request.setAttribute("currentPage", page);
		return new Dispatcher.Forward("products.jsp");
	}
}
