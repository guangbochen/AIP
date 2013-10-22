package com.guangbo.chen.client;

import java.util.List;
import server.chen.guangbo.com.*;

public class SupplierClient {
	private static List<PaidOrder> orders;
	
	public static void main(String args[])
	{
		SupplierServerService spc = new SupplierServerService();
		SupplierServer sc = spc.getSupplierServerPort();
		orders = sc.listOrders();
		System.out.println("Start Client");
		if(orders.isEmpty())
			System.out.println("is empty");
		for(PaidOrder o : orders)
		{
			System.out.println(o.getOrderNumber());
		}
		
	}

}
