package com.guangbo.chen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guangbo.chen.index.action.IndexAction;

/**
 * Servlet implementation class IndexServlet
 * this class is controller that manages all the request from index pages
 */
public class IndexController extends HttpServlet 
{
	private Map<String,Action> actions;
	
	/**
	 * PostConstructor to inject the required EJB bean and initialize the action maps
	 */
	@PostConstruct
	public void init() {
		actions = new HashMap<String,Action>();
		actions.put("index", new IndexAction());
		//set default action to index page
		actions.put(null, actions.get("index"));
	}
	
	/**
	 * this method calls the doPost method
	 * @param request, HttpServletRequest
	 * @param response, HttpServletResponse
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * this method handles all the request from index page and forwarding to a specific page
	 * @param request, HttpServletRequest
	 * @param response, HttpServletResponse
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
			e.printStackTrace();
			Action action = actions.get("index");
			action.execute(request).dispatch(request, response);
		}
	}

}
