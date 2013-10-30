package com.guangbo.chen.server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.guangbo.chen.server.model.PaidOrder;

/**
 * this is interface for supplier webService server and it describes its methods
 */
@WebService(name = "SupplierServer", targetNamespace = "http://com.guangbo.chen.server/")
public interface SupplierServer {
	
	/**
	 * this methods returns a list of paid orders
	 * @return List, list of paid orders
	 */
	@WebMethod(operationName = "listOrders")
	public List<PaidOrder> listPaidOrders();
	
	/**
	 * this method update the oder status to SENT
	 * @param orderNumber, string order number
	 * @param status, string order status
	 * @return string message, result message
	 */
	@WebMethod(operationName = "updateOrder")
	public String updateOrder(@WebParam(name="orderNumber")String orderNumber, 
			@WebParam(name="status")String status);
	
	@WebMethod(operationName = "isAuthorised")
	public boolean isAuthorised();
}