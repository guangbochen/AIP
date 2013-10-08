package com.guangbo.chen.order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanDAO;
import com.guangbo.chen.jpa.Orderline;

public class CheckCartAction implements Action{
	private CartBeanDAO cartBean;
	
	public CheckCartAction(CartBeanDAO cartBean) {
		this.cartBean = cartBean;
	}

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		ArrayList<Orderline> orderList = (ArrayList<Orderline>) cartBean.getOrderList();
        request.setAttribute("shoppingCart", orderList);
		return new Dispatcher.Forward("orders.jsp");
	}
}
