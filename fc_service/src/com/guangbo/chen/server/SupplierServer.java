package com.guangbo.chen.server;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.guangbo.chen.server.model.PaidOrder;

@WebService(name = "SupplierServer", targetNamespace = "http://com.guangbo.chen.server/")
public interface SupplierServer {
	
	@WebMethod(operationName = "listOrders")
	public List<PaidOrder> listPaidOrders();
	
	@WebMethod(operationName = "updateOrder")
	public String updateOrder(@WebParam(name="orderNumber")String orderNumber, 
			@WebParam(name="status")String status);
}