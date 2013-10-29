package com.guangbo.chen.dao;

import java.util.List;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;


/**
 * this is Order JPA implementation interface 
 * @author guangbo
 */
public interface OrderJpaDAO {

	//Basic CRUD for OrderEjbDao
	public void addOrder(List<Orderline> orderList, Order order);
	
	//searching and getting method
	public String getUniqueOrderNum();
	public double getGrandTotal(List<Orderline> ols);
	public Order findOrderByOrderNumAndSurname(String orderNum, String surname);
	
	
	//admin searching methods
	public List<Order> findOutstandingOrders();
	public Order findOrderByOrderNumber(String orderNumber);
	public void updateOrderStatus(String orderNumber, String status);
	
	
	//supplier searching and update method
	public List<Order> findPaidOrders();
	public boolean updatePaidOrder(String orderNumber, String status);
}
