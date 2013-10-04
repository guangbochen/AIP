package com.guangbo.chen.order.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;

public class ViewOrderAction implements Action{

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		return new Dispatcher.Forward("viewOrder.jsp");
	}

}
