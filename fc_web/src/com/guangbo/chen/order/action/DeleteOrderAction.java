package com.guangbo.chen.order.action;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Orderline;

public class DeleteOrderAction implements Action{
	private CartBeanRemote cartBean;
	
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		//find stateful cart session bean
		cartBean = (CartBeanRemote) request.getSession().getAttribute("cartBean");
		
		//remove order via product id
		if(cartBean != null)
		{
			int pid = Integer.parseInt(request.getParameter("pid"));
			cartBean.deleteOrder(pid);
			
			//forward data to the request
			request.setAttribute("shoppingCart", cartBean.getOrderList());
			request.setAttribute("grandTotal", cartBean.getGrandTotal());
		}
		
		return new Dispatcher.Forward("orders.jsp");
	}

}
