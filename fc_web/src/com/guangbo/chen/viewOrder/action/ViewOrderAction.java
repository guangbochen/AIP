package com.guangbo.chen.viewOrder.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;

/**
 * this class handles HTTP request from the view order page
 * @author guangbo
 */
public class ViewOrderAction implements Action{

	/**
	 * this method forwarding user to the view order page that allows user to find
	 * specific order via order number and surname
	 * @param request, HttpServreletRequest
	 * @return Dispatcher, Dispatcher forwarding to the viewOrder page
	 */
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		return new Dispatcher.Forward("viewOrder.jsp");
	}

}
