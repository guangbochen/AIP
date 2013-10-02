package com.guangbo.chen.product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.ProductDAO;
import com.guangbo.chen.jpa.Product;

public class CategoryAction implements Action{
	private ProductDAO pdao;
	private final static int recordsPerPage = 8;
	private int page;
	private int noOfRecords;
	private int noOfPages;
	private ArrayList<Product> productList;
	
	
	public CategoryAction(ProductDAO pdao) {
		this.pdao = pdao;
	}
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		//initialize variables
		productList = null;
		page =1;
		noOfRecords = 0;
		noOfPages = 0;
		
		String category = request.getParameter("category");
		
		//displays all of the products
		noOfRecords = pdao.findAllByCategory(category).size();
		// calculates the total number of pages for paging
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
		//if HTTP request contains page parameter
		if(request.getParameter("page") != null) {
			try 
			{
				page = Integer.parseInt(request.getParameter("page"));
				if(page<1 || page > noOfPages) page =1;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//get a list product categories for category filter
		productList = (ArrayList<Product>) pdao.findAllCategoryByPagination(category,(page-1)*recordsPerPage,recordsPerPage);
		ArrayList<String> cateList = (ArrayList<String>) pdao.findAllCategory();
		
		//forward list of product to the products page
		request.setAttribute("productList", productList);
        request.setAttribute("cateList", cateList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
		request.setAttribute("category", category);
		return new Dispatcher.Forward("products.jsp");
	}

}
