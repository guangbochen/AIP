package com.guangbo.chen.server;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.*;
import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.server.model.PaidOrder;


/**
 * This class implements supplierServer interface,
 * this is supplier webSerive allows client to query a list or orders or update specific order
 */
@WebService(portName = "SupplierServerPort", 
	serviceName = "SupplierServerService", 
	targetNamespace = "http://com.guangbo.chen.server/", 
	endpointInterface = "com.guangbo.chen.server.SupplierServer")
public class SupplierServerImpl implements SupplierServer {
	
	@EJB (name="OrderEjb",mappedName="ejb/order")
	private OrderBeanRemote oBean; 

	/**
	 * this method returns a list of paid orders
	 * @return, list of paid orders
	 */
	@Override
	public List<PaidOrder> listPaidOrders() {
		return getPaidOrders();
	}

	/**
	 * this method update the oder status to SENT
	 * @param orderNumber, string order number
	 * @param status, string order status
	 * @return string message, result message
	 */
	@Override
	public String updateOrder(String orderNumber, String status) {
		String message = "";
		//return true if updating is successful
		if(oBean.updatePaidOrder(orderNumber, status))
			message =" Thanks, You have update order"+ orderNumber 
			+ " to status ["+ status +"] Successfully.";
		else
			message = " Invalid Order Number or Status, Please try again";
		return message;
	}
	
	
	/**
	 * this method validates supplier user account
	 * @return true, if supplier is validated
	 */
	@Override
	public boolean isAuthorised() {
		return true;
	}
	
	/**
	 * this method add and returns a list of paid orders
	 * @return, list of paid order
	 */
	private List<PaidOrder> getPaidOrders() {
		List<PaidOrder> paidOrders= new ArrayList<PaidOrder>();
		
		List<Order> orders = oBean.findPaidOrders();
		if(orders != null)
		{
			for(Order o : orders)
			{
				//initalize paidOrder instance
				PaidOrder po = new PaidOrder();
				
				//add value to the paidOrder's instance fields
				po.setOrderNumber(o.getOrderNumber());
				po.setSurname(o.getSurname());
				po.setStatus(o.getStatus());
				int totalCount = getTotalCount(o.getOrderlines());
				po.setTotalCount(totalCount);
				double grandTotal = oBean.getGrandTotal(o.getOrderlines());
				po.setGrandTotal(grandTotal);
				paidOrders.add(po);
			}
		}
		return paidOrders;
	}
	
	/**
	 * this method count and return s the total count of quantity of the order
	 * @param ols, List of orderline
	 * @return, int totalCount
	 */
	private int getTotalCount(List<Orderline> ols)
	{
		int totalCount = 0;
		for(Orderline ol : ols)
		{
			totalCount += ol.getQuantity();
		}
		return totalCount;
	}

	
}
