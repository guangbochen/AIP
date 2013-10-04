package com.guangbo.chen.product.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;

public class AddToCartAction implements Action {

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new Dispatcher.Forward("orders.jsp");
	}

}
