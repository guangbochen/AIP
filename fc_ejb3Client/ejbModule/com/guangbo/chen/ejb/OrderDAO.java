package com.guangbo.chen.ejb;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

@Remote
public interface OrderDAO {
	
	//Basic CRUD for OrderEjbDao
	public void addOrder(ArrayList<Orderline> orderList, Order order);
	
	//searching and getting method
	public String getUniqueOrderNum();
	public double getGrandTotal(ArrayList<Orderline> orderList);

}
