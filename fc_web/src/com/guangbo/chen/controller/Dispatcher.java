package com.guangbo.chen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * this is abstract class that manages the dispatcher action via its derived class
 * @author guangbo
 */
public abstract class Dispatcher {
	public abstract void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	/**
	 * This dispatcher forwards to a given page stored to "WEB-INF/views/". Pages
	 * are stored here so they cannot be accessed directly via a URL and must be
	 * forwarded to by the controller.
	 */
	public static class Forward extends Dispatcher {
		private String path;

		public Forward(String page) {
			path = "WEB-INF/views/" + page;
		}

		public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	/**
	 * This dispatcher redirects to a given url.
	 */
	public static class Redirect extends Dispatcher {
		private String url;

		public Redirect(String url) {
			this.url = url;
		}

		public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.sendRedirect(url);
		}
	}

}
