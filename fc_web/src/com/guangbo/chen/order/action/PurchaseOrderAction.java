package com.guangbo.chen.order.action;

import javax.servlet.http.HttpServletRequest;
import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;

/**
 * this class handles HTTP request from the order page
 * @author guangbo
 */
public class PurchaseOrderAction implements Action{
	private CartBeanRemote cartBean;
	
	/**
	 * this method forwarding the user to the purchasing page to complete the order purchase
	 * @param request, HttpServreletRequest
	 * @return Dispatcher, Dispatcher forwarding to the purchase page
	 */
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
