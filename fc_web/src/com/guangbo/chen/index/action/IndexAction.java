package com.guangbo.chen.index.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;

/**
 * this class handles HTTP request from the index page
 * @author guangbo
 */
public class IndexAction implements Action {

	/**
	 * this method forwarding user to the index page
	 * @param request, HttpServreletRequest
	 * @return Dispatcher, Dispatcher forwarding to the index page
	 */
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		return new Dispatcher.Forward("index.jsp");
	}

}
