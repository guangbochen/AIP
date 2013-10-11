package com.guangbo.chen.dao;

import java.util.ArrayList;
import java.util.List;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

public interface OrderJpaDAO {

	//Basic CRUD for OrderEjbDao
	public void addOrder(List<Orderline> orderList, Order order);
	
	//searching and getting method
	public String getUniqueOrderNum();
	public double getGrandTotal(List<Orderline> ols);
	public Order findOrderByOrderNumAndSurname(String orderNum, String surname);
	
	
	//admin searching methods
	public List<Order> findOutstandingOrders();
}
