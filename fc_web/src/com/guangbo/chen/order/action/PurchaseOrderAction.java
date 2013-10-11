package com.guangbo.chen.order.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;
import com.guangbo.chen.jpa.Orderline;

public class PurchaseOrderAction implements Action{
	private CartBeanRemote cartBean;
	
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		//find stateful cart session bean
		cartBean = (CartBeanRemote) request.getSession().getAttribute("cartBean");
		
		if((cartBean == null) || 
			(cartBean.getOrderList() == null || cartBean.getOrderList().isEmpty()))
		{
				//set empty order message
				request.setAttribute("emptyOrder", "Please select at least one product to purchase.");
				request.setAttribute("grandTotal", 0.0);
				return new Dispatcher.Forward("orders.jsp");
		}
		else
		{
			return new Dispatcher.Forward("purchase.jsp");
		}
	}

}
