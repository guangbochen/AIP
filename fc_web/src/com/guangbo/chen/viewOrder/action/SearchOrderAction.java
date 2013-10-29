package com.guangbo.chen.viewOrder.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

/**
 * this class handles HTTP request from the view order page
 * @author guangbo
 */
public class SearchOrderAction implements Action{
	private OrderBeanRemote oBean;
	
	/**
	 * constructor to inject the order EJB bean
	 * @param oBean, Order EJB bean
	 */
	public SearchOrderAction(OrderBeanRemote oBean) {
		this.oBean = oBean;
	}

	/**
	 * this method forwarding user to the view order page that allows user to find
	 * specific order via order number and surname
	 * @param request, HttpServreletRequest
	 * @return Dispatcher, Dispatcher forwarding to the viewOrder page
	 */
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		String number = request.getParameter("orderNumber");
		String name = request.getParameter("surname");
		
		//validates the searching order number and surname
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
			//find the order via order number and surname
			Order order = oBean.findOrderByOrderNumAndSurname(number, name);
			List<Orderline> ols = (List<Orderline>) order.getOrderlines();
			if(ols == null) request.setAttribute("message", " Invalid order number or surname");
			double grandTotal = oBean.getGrandTotal(ols);
			request.setAttribute("order", order);
			request.setAttribute("orderlines", ols);
			request.setAttribute("grandTotal", grandTotal);
		}
		return new Dispatcher.Forward("viewOrder.jsp");
	}

}
