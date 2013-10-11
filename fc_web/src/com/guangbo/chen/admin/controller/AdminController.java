package com.guangbo.chen.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guangbo.chen.admin.action.OutstandingOrderAction;
import com.guangbo.chen.controller.Action;
import com.guangbo.chen.ejb.OrderBeanRemote;

/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet {
	@EJB (name="OrderEjb",mappedName="ejb/order")
	private OrderBeanRemote oBean; 
	private Map<String,Action> actions;
	
	@PostConstruct
	public void init() {
		actions = new HashMap<String,Action>();
		actions.put("viewOrder", new OutstandingOrderAction(oBean));
		//set default action to index page
		actions.put(null, actions.get("viewOrder"));
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
