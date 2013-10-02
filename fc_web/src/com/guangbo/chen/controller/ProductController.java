package com.guangbo.chen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guangbo.chen.ejb.ProductDAO;
import com.guangbo.chen.product.action.CategoryAction;
import com.guangbo.chen.product.action.ProductsAction;

public class ProductController extends HttpServlet{
	@EJB (name="productEjb",mappedName="ejb/product")
	private ProductDAO pdao;
	private Map<String,Action> actions;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		actions = new HashMap<String,Action>();
		actions.put("products", new ProductsAction(pdao));
		actions.put("category", new CategoryAction(pdao));
		//set default action to products page
		actions.put(null, actions.get("products"));
		
		doPost(request, response);
	}

	/**
	 * this method handles product post requests
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			Action action = actions.get(request.getParameter("action"));
			action.execute(request).dispatch(request, response);
		}
		catch(Exception e)
		{
			Action action = actions.get("products");
			action.execute(request).dispatch(request, response);
		}
	}
	
}
