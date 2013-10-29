package com.guangbo.chen.purchase.action;

import javax.servlet.http.HttpServletRequest;

import com.guangbo.chen.controller.Action;
import com.guangbo.chen.controller.Dispatcher;
import com.guangbo.chen.jpa.Order;

/**
 * this class handles HTTP request from the purchase page
 * @author guangbo
 */
public class PurchaseAction implements Action {

	/**
	 * this method validates the customer order details and handle the pruchase actions
	 * @param request, HttpServreletRequest
	 * @return Dispatcher, Dispatcher forwarding to the confirmPurchase check page
	 */
	@Override
	public Dispatcher execute(HttpServletRequest request) {
		boolean validation = true;
		Order order = null;
		try
		{
			//get customer details
			String title = request.getParameter("title");
			String surname = request.getParameter("surname");
			String givenName = request.getParameter("givenName");
			String email = request.getParameter("email");
			String unit = request.getParameter("unit");
			String street = request.getParameter("street");
			String state = request.getParameter("state");
			String suburb = request.getParameter("suburb");
			String postcode = request.getParameter("postcode");
			String country = request.getParameter("country");
			String payment = request.getParameter("creditCard");
			order = new Order(title,surname,givenName,email,
					unit,street,state,suburb,postcode,country,payment);
			validation = order.formValidation(title,surname,givenName,email,
					unit,street,state,suburb,postcode,country,payment);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//if the form has empty input
		if(!validation)
		{
			request.setAttribute("order", order);
			request.setAttribute("isempty", " Can't be empty");
			return new Dispatcher.Forward("purchase.jsp");
		}
		else
		{
			//forward to confirms purchasing order
			request.getSession().setAttribute("order", order);
			return new Dispatcher.Forward("confirmPurchase.jsp");
		}
}

}
