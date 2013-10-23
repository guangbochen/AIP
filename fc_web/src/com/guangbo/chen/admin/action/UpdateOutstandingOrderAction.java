package com.guangbo.chen.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.OrderBeanRemote;

public class UpdateOutstandingOrderAction implements Action{
	private OrderBeanRemote oBean;
	
	public UpdateOutstandingOrderAction(OrderBeanRemote oBean) {
		this.oBean = oBean;
	}

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		try{
			String orderNumber = request.getParameter("orderNumber");
			String status = request.getParameter("status");
			if(!orderNumber.equals("") && !status.equals(""))
			{
				oBean.updateOrderStatus(orderNumber, status);
				request.setAttribute("updateSuccess", "You have update '" +
						orderNumber +"' status to '"+ status +"' successfully. ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String user = request.getRemoteUser();
		request.setAttribute("user", user);
		return new Dispatcher.Forward("admin/updateNotice.jsp");
	}

}
