package com.guangbo.chen.order.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;
import com.guangbo.chen.jpa.Orderline;

public class CheckCartAction implements Action{
	private CartBeanRemote cartBean;
	private List<Orderline> orderList;
	private double grandTotal;

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		grandTotal = 0.0;
		cartBean = (CartBeanRemote) request.getSession().getAttribute("cartBean");
		if(cartBean != null)
		{
			orderList = (List<Orderline>) cartBean.getOrderList();
	        request.setAttribute("shoppingCart", cartBean.getOrderList() );
			grandTotal = cartBean.getGrandTotal();
		}
		request.setAttribute("grandTotal", grandTotal);
		return new Dispatcher.Forward("orders.jsp");
	}
}
