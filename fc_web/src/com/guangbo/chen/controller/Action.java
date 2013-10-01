package com.guangbo.chen.controller;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	public Dispatcher execute(HttpServletRequest request);
}
