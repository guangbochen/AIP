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
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.index.action.IndexAction;
import com.guangbo.chen.purchase.action.ProcessPurchaseAction;
import com.guangbo.chen.purchase.action.PurchaseAction;

/**
 * Servlet implementation class PurchaseController
 */
public class PurchaseController extends HttpServlet {
	@EJB(name = "OrderEjb", mappedName = "ejb/order")
	private OrderBeanRemote obean;
	private Map<String,Action> actions;
	
	
	@PostConstruct
    public void init() {
		actions = new HashMap<String,Action>();
		actions.put("index", new IndexAction());
		actions.put("purchase", new PurchaseAction());
		actions.put("process", new ProcessPurchaseAction(obean));
		//set default action to index page
		actions.put(null, actions.get("index"));
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Action action = actions.get(request.getParameter("action"));
			action.execute(request).dispatch(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Action action = actions.get("index");
			action.execute(request).dispatch(request, response);
		}
	}

}
