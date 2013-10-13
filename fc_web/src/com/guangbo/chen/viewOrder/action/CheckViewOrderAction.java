package com.guangbo.chen.viewOrder.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

public class CheckViewOrderAction implements Action{
	private OrderBeanRemote oBean;
	
	public CheckViewOrderAction(OrderBeanRemote oBean) {
		this.oBean = oBean;
	}

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		String number = request.getParameter("orderNumber");
		String name = request.getParameter("surname");
		if(number.equals("") || name.equals(""))
		{
			request.setAttribute("emptyNum", "empty order number");
			request.setAttribute("emptyName", "empty surname");
			request.setAttribute("error", "has-error");
			//save user input to avoid repeating 
			request.setAttribute("number", number);
			request.setAttribute("name", name);
		}
		else
		{
			Order order = oBean.findOrderByOrderNumAndSurname(number, name);
			List<Orderline> ols = (List<Orderline>) order.getOrderlines();
			double grandTotal = oBean.getGrandTotal(ols);
			request.setAttribute("order", order);
			request.setAttribute("orderlines", ols);
			request.setAttribute("grandTotal", grandTotal);
		}
		return new Dispatcher.Forward("viewOrder.jsp");
	}

}
