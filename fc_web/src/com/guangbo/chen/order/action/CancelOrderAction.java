package com.guangbo.chen.order.action;

import javax.servlet.http.HttpServletRequest;
import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;

public class CancelOrderAction implements Action {
	private CartBeanRemote cartBean;
	
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		//find stateful cart session bean
		cartBean = (CartBeanRemote) request.getSession().getAttribute("cartBean");
		
		//erase cart session bean
		request.getSession().setAttribute("cartBean", null);
		
		//update the grand Total and empty shopping cart
		request.setAttribute("grandTotal", 0.0);
        request.setAttribute("shoppingCart", null);
		return new Dispatcher.Forward("orders.jsp");
		
	}

}
