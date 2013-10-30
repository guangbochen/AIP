package com.guangbo.chen.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Order;

/**
 * this class handles orders request from the admin page
 * it returns a list of outstanding order with status "PAID" and "ORDERED"
 * @author guangbo
 */
public class OutstandingOrderAction implements Action{
	private OrderBeanRemote oBean;
	private double grandTotal;
	
	/**
	 * constructor to inject the order EJB bean
	 * @param oBean, Order EJB bean
	 */
	public OutstandingOrderAction(OrderBeanRemote oBean) {
		this.oBean = oBean;
	}

	/**
	 * this method forwarding a list of outstanding orders to the admin page
	 * @param request, HttpServreletRequest
	 * @return Dispatcher, Dispatcher forwarding to the admin page
	 */
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		List<Order> orders = oBean.findOutstandingOrders();
		request.setAttribute("orders", orders);
		String user = request.getRemoteUser();
		request.setAttribute("user", user);
		return new Dispatcher.Forward("admin/admin.jsp");
	}
	

}
