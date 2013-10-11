package com.guangbo.chen.purchase.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

public class ProcessPurchaseAction implements Action{
	private CartBeanRemote cartBean;
	private OrderBeanRemote obean;
	private Order order;

	public ProcessPurchaseAction(OrderBeanRemote obean) {
		this.obean = obean;
	}

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		cartBean = (CartBeanRemote) request.getSession().getAttribute("cartBean");
		order= (Order) request.getSession().getAttribute("order");
		if(cartBean != null)
		{
			List<Orderline> orderList = (List<Orderline>) cartBean.getOrderList();
			obean.addOrder(orderList, order);
			
			String name = order.getGivenName() + " " +order.getSurname();
			//display ordering notice for customer
        	request.setAttribute("orderNumber", obean.getUniqueOrderNum());
        	request.setAttribute("name", name);
        	
        	//once saved order into database successfully clean up the session beans
			request.getSession().setAttribute("cartBean", null);
			request.getSession().setAttribute("order", null);
        	
			return new Dispatcher.Forward("purchaseNotice.jsp");
		}
		else
		{
			return new Dispatcher.Forward("error.jsp");
		}
		
	}

}
