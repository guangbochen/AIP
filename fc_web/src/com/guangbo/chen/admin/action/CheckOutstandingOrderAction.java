package com.guangbo.chen.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Order;

public class CheckOutstandingOrderAction implements Action {
	private OrderBeanRemote oBean;
	private String orderNumber;
	
	public CheckOutstandingOrderAction(OrderBeanRemote oBean) {
		this.oBean = oBean;
	}

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		try{
			orderNumber = request.getParameter("nu");
			if(orderNumber.equals(""))
			{
				request.setAttribute("isempty", " Empty");
				request.setAttribute("error", "has-error");
			}
			else
			{
				Order order = oBean.findOrderByOrderNumber(orderNumber);
				if(order == null) request.setAttribute("message", " Invalid order number '"+ orderNumber +"'");
				request.setAttribute("order", order);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//display login user name
		String user = request.getRemoteUser();
		request.setAttribute("user", user);
		
		return new Dispatcher.Forward("admin/admin_update.jsp");
	}

}
