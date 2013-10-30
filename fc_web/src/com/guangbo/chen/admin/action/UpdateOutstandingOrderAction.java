package com.guangbo.chen.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.OrderBeanRemote;

/**
 * this class handles orders request from the admin page
 * it updates the order status via order number
 * @author guangbo
 */
public class UpdateOutstandingOrderAction implements Action{
	private OrderBeanRemote oBean;
	
	/**
	 * constructor to inject the order EJB bean
	 * @param oBean, Order EJB bean
	 */
	public UpdateOutstandingOrderAction(OrderBeanRemote oBean) {
		this.oBean = oBean;
	}

	/**
	 * this method updating the order status
	 * @param request, HttpServreletRequest
	 * @return Dispatcher, Dispatcher forwarding to the admin notice page
	 */
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		try{
			String orderNumber = request.getParameter("orderNumber");
			String status = request.getParameter("status");
			
			//if is empty order number set error message
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
