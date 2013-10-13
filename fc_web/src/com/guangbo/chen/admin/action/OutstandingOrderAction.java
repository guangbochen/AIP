package com.guangbo.chen.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Order;

public class OutstandingOrderAction implements Action{
	private OrderBeanRemote oBean;
	private double grandTotal;
	public OutstandingOrderAction(OrderBeanRemote oBean) {
		this.oBean = oBean;
	}

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		List<Order> orders = oBean.findOutstandingOrders();
		request.setAttribute("orders", orders);
		return new Dispatcher.Forward("admin/admin.jsp");
	}
	

}
