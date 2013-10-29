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

import com.guangbo.chen.admin.action.CheckOutstandingOrderAction;
import com.guangbo.chen.admin.action.OutstandingOrderAction;
import com.guangbo.chen.admin.action.UpdateOutstandingOrderAction;
import com.guangbo.chen.ejb.OrderBeanRemote;

/**
 * Servlet implementation class AdminController
 * this class is controller that manages all the request from admin pages
 */
public class AdminController extends HttpServlet {
	@EJB (name="OrderEjb",mappedName="ejb/order")
	private OrderBeanRemote oBean; 
	private Map<String,Action> actions;
	
	/**
	 * PostConstructor to inject the order EJB bean and initialize the action maps
	 */
	@PostConstruct
	public void init() {
		actions = new HashMap<String,Action>();
		actions.put("viewOrder", new OutstandingOrderAction(oBean));
		actions.put("check", new CheckOutstandingOrderAction(oBean));
		actions.put("update", new UpdateOutstandingOrderAction(oBean));
		//set default action to view outstanding order page
		actions.put(null, actions.get("viewOrder"));
	}

	/**
	 * this method calls the doPost method
	 * @param request, HttpServletRequest
	 * @param response, HttpServletResponse
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * this method handles all the request from admin page and forwarding to a specific page
	 * @param request, HttpServletRequest
	 * @param response, HttpServletResponse
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Action action = actions.get(request.getParameter("action"));
			action.execute(request).dispatch(request, response);
		}
		catch(Exception e)
		{
			Action action = actions.get("viewOrder");
			action.execute(request).dispatch(request, response);
			e.printStackTrace();
		}
	}

}
