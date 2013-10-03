package com.guangbo.chen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guangbo.chen.order.action.CancelOrderAction;
import com.guangbo.chen.order.action.CheckCartAction;
import com.guangbo.chen.order.action.DeleteOrderAction;
import com.guangbo.chen.order.action.PurchaseOrderAction;
import com.guangbo.chen.order.action.UpdateOrderAction;
import com.guangbo.chen.order.action.ViewOrderAction;

public class OrderController extends HttpServlet {
	private Map<String,Action> actions;
	
	
	@PostConstruct
    public void init() {
		actions = new HashMap<String,Action>();
		actions.put("default", new CheckCartAction());
		actions.put("view",		 new ViewOrderAction());
		actions.put("update", 	 new UpdateOrderAction());
		actions.put("delete", 	 new DeleteOrderAction());
		actions.put("cancel", 	 new CancelOrderAction());
		actions.put("purchase",  new PurchaseOrderAction());
		//set default action to index page
		actions.put(null, actions.get("default"));
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
			Action action = actions.get("default");
			action.execute(request).dispatch(request, response);
		}
	}

}
