package com.guangbo.chen.order.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;

public class CheckCartAction implements Action{
	
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		return new Dispatcher.Forward("orders.jsp");
	}
}
