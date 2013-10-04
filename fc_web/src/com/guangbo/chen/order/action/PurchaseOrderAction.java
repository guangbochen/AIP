package com.guangbo.chen.order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.jpa.Orderline;

public class PurchaseOrderAction implements Action{

	
	@Override
	public Dispatcher execute(HttpServletRequest request) {
//		ArrayList<Orderline> orderList= new ArrayList<Orderline>();
//		HttpSession sess = request.getSession();
//		orderList = (ArrayList<Orderline>) sess.getAttribute("shoppingCart");
//		if(orderList == null || orderList.isEmpty())
//		{
//			//set empty order message
//			request.setAttribute("emptyOrder", "Please select at least one product to purchase.");
//			return new Dispatcher.Forward("orders.jsp");
//		}
//		else
//		{
			return new Dispatcher.Forward("purchase.jsp");
//		}
	}

}
