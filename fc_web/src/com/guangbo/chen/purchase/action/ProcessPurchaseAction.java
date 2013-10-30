package com.guangbo.chen.purchase.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

/**
 * this class handles HTTP request from the process page
 * @author guangbo
 */
public class ProcessPurchaseAction implements Action{
	private CartBeanRemote cartBean;
	private Order order;

	/**
	 * this method handle order processing action if user confirmed to purchase the order
	 * @param request, HttpServreletRequest
	 * @return Dispatcher, Dispatcher forwarding to the PurchaseNotice page if is done,
	 * otherwise redirect to the error page
	 */
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		//find the EJB cart session bean
		cartBean = (CartBeanRemote) request.getSession().getAttribute("cartBean");
		order= (Order) request.getSession().getAttribute("order");
		if(cartBean != null)
		{
			List<Orderline> orderList = (List<Orderline>) cartBean.getOrderList();
			//obean.addOrder(orderList, order);
			cartBean.addOrder(orderList, order);
			
			String name = order.getGivenName() + " " +order.getSurname();
			//display ordering notice for customer
        	request.setAttribute("orderNumber", cartBean.getUniqueOrderNum());
        	request.setAttribute("name", name);
        	
        	//once saved order into database successfully clean up the session beans
			request.getSession().setAttribute("cartBean", null);
			request.getSession().setAttribute("order", null);
        	
			return new Dispatcher.Forward("purchaseNotice.jsp");
		}
		else
		{
			return new Dispatcher.Redirect("404.jsp");
		}
		
	}

}
