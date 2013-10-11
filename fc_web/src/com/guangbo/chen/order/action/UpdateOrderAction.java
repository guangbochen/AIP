package com.guangbo.chen.order.action;

import javax.servlet.http.HttpServletRequest;
import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;

public class UpdateOrderAction implements Action {
	private CartBeanRemote cartBean;
	
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		//find stateful cart session bean
		cartBean = (CartBeanRemote) request.getSession().getAttribute("cartBean");
		
		if(cartBean != null)
		{
			try
			{
				int pid = Integer.parseInt(request.getParameter("pid"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				//validate input quantity should be greater than 1
				if(quantity >0) cartBean.updateOrder(quantity, pid);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		//update the order list and granTotal
        request.setAttribute("shoppingCart", cartBean.getOrderList() );
		request.setAttribute("grandTotal", cartBean.getGrandTotal());
        
		return new Dispatcher.Forward("orders.jsp");
	}

}
