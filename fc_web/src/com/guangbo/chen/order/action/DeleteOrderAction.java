package com.guangbo.chen.order.action;

import javax.servlet.http.HttpServletRequest;
import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;

/**
 * this class handles HTTP request from the order page
 * @author guangbo
 */
public class DeleteOrderAction implements Action{
	private CartBeanRemote cartBean;
	
	/**
	 * this method delete a specific orderlines that belongs to the customer via product id
	 * @param request, HttpServreletRequest
	 * @return Dispatcher, Dispatcher forwarding to the orders page
	 */
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
