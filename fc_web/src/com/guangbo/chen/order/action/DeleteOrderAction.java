package com.guangbo.chen.order.action;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Orderline;

public class DeleteOrderAction implements Action{
	private OrderBeanRemote odao;
	
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		ArrayList<Orderline> orderList= new ArrayList<Orderline>();
		HttpSession sess = request.getSession();
		orderList = (ArrayList<Orderline>) sess.getAttribute("shoppingCart");
		try
		{
			int pid = Integer.parseInt(request.getParameter("pid"));
			for (Iterator<Orderline> it = orderList.iterator(); it.hasNext(); )
			{
				Orderline op = (Orderline) it.next();  
				if(op.getProduct().getId() == pid )
				{
					it.remove();
				}
			}

			//update the grand Total and the shopping cart
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
