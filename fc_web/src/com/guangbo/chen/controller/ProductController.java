package com.guangbo.chen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.guangbo.chen.controller.action.IndexAction;
import com.guangbo.chen.ejb.ProductDAO;
import com.guangbo.chen.product.action.ProductsAction;

@WebServlet("/products")
public class ProductController extends HttpServlet{
	@EJB (name="productEjb",mappedName="ejb/product")
	private ProductDAO pdao; 
	@PersistenceContext
	private EntityManager em;
	private Map<String,Action> actions;
	
	public ProductController() {
		pdao.setEntityManager(em);
		actions = new HashMap<String,Action>();
		actions.put("index", new IndexAction());
		actions.put("products", new ProductsAction(pdao));
		//set default action to index page
		actions.put(null, actions.get("products"));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * this method handles index post requests
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Action action = actions.get(request.getParameter("action"));
		action.execute(request).dispatch(request, response);
	}
	
}
