package com.guangbo.chen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guangbo.chen.ejb.CartBeanRemote;
import com.guangbo.chen.ejb.ProductBeanRemote;
import com.guangbo.chen.product.action.AddToCartAction;
import com.guangbo.chen.product.action.CategoryAction;
import com.guangbo.chen.product.action.ProductsAction;


public class ProductController extends HttpServlet{
	@EJB (name="productEjb",mappedName="ejb/product")
	private ProductBeanRemote pdao;
	private CartBeanRemote cartBean;
	private Map<String,Action> actions;
	
	@PostConstruct
	public void Init() {
		actions = new HashMap<String,Action>();
		actions.put("products", new ProductsAction(pdao));
		actions.put("category", new CategoryAction(pdao));
		actions.put("order", 	new AddToCartAction());
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
			initCartBean(request);
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
	
	private void initCartBean(HttpServletRequest request)
	{
		cartBean = (CartBeanRemote) request.getSession().getAttribute("cartBean");
		//if Cart Ejb Bean is not presented in the HTTP session,fetch new one from container
		if(cartBean == null)
		{
			try
			{
				InitialContext ic = new InitialContext();
				String mappedName = "ejb/cartBean";
				String weblogicExtra = "#" + CartBeanRemote.class.getName();
				cartBean  = (CartBeanRemote) ic.lookup(mappedName+weblogicExtra);
				
				// put EJB in HTTP session for future servlet calls
				request.getSession().setAttribute("cartBean", cartBean);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
}
