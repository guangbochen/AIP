package com.guangbo.chen.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;

public class IndexAction implements Action {

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		return new Dispatcher.Forward("index.jsp");
	}

}
