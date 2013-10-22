package com.guangbo.chen.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.*;

import com.guangbo.chen.ejb.OrderBeanRemote;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.server.model.PaidOrder;

@WebService(portName = "SupplierServerPort", 
	serviceName = "SupplierServerService", 
	targetNamespace = "http://com.guangbo.chen.server/", 
	endpointInterface = "com.guangbo.chen.server.SupplierServer")
public class SupplierServerImpl implements SupplierServer {
	
	@EJB (name="OrderEjb",mappedName="ejb/order")
	private OrderBeanRemote oBean; 
	private List<PaidOrder> paidOrders;

	@Override
	public List<PaidOrder> listPaidOrders() {
		return getPaidOrders();
	}

	@Override
	public String updateOrder(String orderNumber, String status) {
		String message = "";
		if(oBean.updatePaidOrder(orderNumber, status))
			message =" Thanks, You have update "+ orderNumber + " to status ["+ status +"] Successfully.";
		else
			message = " Invalid Order Number or Status, Please try again";
		return message;
	}
	
	
	/**
	 * this method add and returns a list of paid orders
	 * @return, list of paid order
	 */
	private List<PaidOrder> getPaidOrders() {
		List<Order> orders = oBean.findPaidOrders();
		paidOrders = new ArrayList<PaidOrder>();
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
		return null;
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
