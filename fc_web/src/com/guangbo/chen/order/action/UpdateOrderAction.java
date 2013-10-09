package com.guangbo.chen.order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Orderline;

public class UpdateOrderAction implements Action {
	private OrderBeanRemote odao;

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		ArrayList<Orderline> orderList= new ArrayList<Orderline>();
		HttpSession sess = request.getSession();
		orderList = (ArrayList<Orderline>) sess.getAttribute("shoppingCart");
		try
		{
			int pid = Integer.parseInt(request.getParameter("pid"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			for(Orderline ol : orderList)
			{
				//update quantity & line total of the order upon product id
				if(ol.getProduct().getId() == pid && quantity >0)
				{
					double price = ol.getProduct().getPrice();
					ol.setQuantity(quantity);
					ol.setLineTotal(price*quantity);
				}
			}
			//update the grand Total
			double grandTotal = odao.getGrandTotal(orderList);
			request.setAttribute("grandTotal", grandTotal);
	        request.setAttribute("shoppingCart", orderList);		
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Dispatcher.Forward("orders.jsp");
	}

}
