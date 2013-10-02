package com.guangbo.chen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.guangbo.chen.controller.action.IndexAction;

/**
 * Servlet implementation class IndexServlet
 * this class handles index request and forwards relevant responses
 */
public class IndexController extends HttpServlet 
{
	private Map<String,Action> actions;
	
	public IndexController() {
		actions = new HashMap<String,Action>();
		actions.put("index", new IndexAction());
		//set default action to index page
		actions.put(null, actions.get("index"));
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
		try
		{
			Action action = actions.get(request.getParameter("action"));
			action.execute(request).dispatch(request, response);
		}
		catch(Exception e)
		{
			Action action = actions.get("index");
			action.execute(request).dispatch(request, response);
		}
	}

}
