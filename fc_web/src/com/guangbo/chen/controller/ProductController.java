package com.guangbo.chen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.guangbo.chen.ejb.CartBeanDAO;
import com.guangbo.chen.ejb.ProductDAO;
import com.guangbo.chen.product.action.AddToCartAction;
import com.guangbo.chen.product.action.CategoryAction;
import com.guangbo.chen.product.action.ProductsAction;


public class ProductController extends HttpServlet{
	@EJB (name="productEjb",mappedName="ejb/product")
	private ProductDAO pdao;
	@EJB (name="CartBean",mappedName="ejb/cartBean")
	private CartBeanDAO cartBean;
	
	private Map<String,Action> actions;
	
	@PostConstruct
	public void Init() {
		actions = new HashMap<String,Action>();
		actions.put("products", new ProductsAction(pdao));
		actions.put("category", new CategoryAction(pdao));
		actions.put("order", 	new AddToCartAction(cartBean));
		//set default action to products page
		actions.put(null, actions.get("products"));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * this method handles product post requests
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Action action = actions.get(request.getParameter("action"));
			action.execute(request).dispatch(request, response);
		}
		catch(Exception e)
		{
			Action action = actions.get("products");
			action.execute(request).dispatch(request, response);
			e.printStackTrace();
		}
	}
	
}
