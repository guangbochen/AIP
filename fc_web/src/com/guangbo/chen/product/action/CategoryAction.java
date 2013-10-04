package com.guangbo.chen.product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		noOfRecords = 0;
		noOfPages = 0;
		
		//filter products via category
		String category = request.getParameter("category");
		if(category.equals("All"))
		{
			noOfRecords = pdao.findAll().size();
			page = getPage(request, noOfRecords);
			productList = (ArrayList<Product>) pdao.findAllByPagination((page-1)*recordsPerPage,recordsPerPage);
		}
		else
		{
			//get a list product categories for category filter
			//displays all of the products
			noOfRecords = pdao.findAllByCategory(category).size();
			page = getPage(request, noOfRecords);
			productList = (ArrayList<Product>) pdao.findAllCategoryByPagination(category,(page-1)*recordsPerPage,recordsPerPage);
		}
		
		// calculates the total number of pages for paging
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ArrayList<String> cateList = (ArrayList<String>) pdao.findAllCategory();
		//forward list of product to the products page
		request.setAttribute("productList", productList);
        request.setAttribute("cateList", cateList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
		request.setAttribute("category", category);
		return new Dispatcher.Forward("products.jsp");
	}
	
	private int getPage(HttpServletRequest request, int maxPage)
	{
		int page = 1;
		//if HTTP request contains page parameter
		if(request.getParameter("page") != null) {
			try 
			{
				page = Integer.parseInt(request.getParameter("page"));
				if(page<1 || page > maxPage) page =1;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return page;
	}

}
