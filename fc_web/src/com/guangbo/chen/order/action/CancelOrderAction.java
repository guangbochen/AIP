package com.guangbo.chen.order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.jpa.Orderline;

public class CancelOrderAction implements Action {

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		ArrayList<Orderline> orderList= new ArrayList<Orderline>();
		HttpSession sess = request.getSession();
		orderList = (ArrayList<Orderline>) sess.getAttribute("shoppingCart");
		//erase shopping cart
		sess.setAttribute("shoppingCart", null);
		orderList = null;
		//update the grand Total and empty shopping cart
		double grandTotal = 0.0;
		request.setAttribute("grandTotal", grandTotal);
        request.setAttribute("shoppingCart", orderList);
		return new Dispatcher.Forward("orders.jsp");
	}

}
