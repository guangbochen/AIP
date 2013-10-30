package com.guangbo.chen.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This Action interface 
 */
public interface Action {
	public Dispatcher execute(HttpServletRequest request);
}
