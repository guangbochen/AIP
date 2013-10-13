package com.guangbo.chen.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.ejb.CartBeanRemote;
import com.guangbo.chen.jpa.Product;

public class AddToCartAction implements Action {
	private CartBeanRemote cartBean;
	
	public AddToCartAction() {
	}

	@Override
	public Dispatcher execute(HttpServletRequest request) {
		
		//find stateful cart session bean
		cartBean = (CartBeanRemote) request.getSession().getAttribute("cartBean");
		
		//get ordering product details from http request
		int pid = Integer.parseInt(request.getParameter("id"));
		String category = request.getParameter("category");
		String code = request.getParameter("code");
		String description  = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//add ordering product to the cart session bean
		Product p = new Product(pid, category, code, description, price);
		cartBean.addToCart(p, quantity);
		
		//forward data to the request
		request.setAttribute("shoppingCart", cartBean.getOrderList());
		request.setAttribute("grandTotal", cartBean.getGrandTotal());
		return new Dispatcher.Forward("orders.jsp");
	}

}
