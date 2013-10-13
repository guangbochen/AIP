package com.guangbo.chen.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.guangbo.chen.dao.OrderJpaDAO;
import com.guangbo.chen.dao.OrderJpaImpl;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

/**
 * Session Bean implementation class OrderEjbDao
 */
@Stateless(name = "OrderEjb", mappedName = "ejb/order")
public class OrderBean implements OrderBeanRemote, OrderBeanLocal {
	@PersistenceContext
	private EntityManager em;
	private OrderJpaDAO odao;

	@PostConstruct
	public void init()
	{
		odao = new OrderJpaImpl(em);
	}
	
	@Override
	public void addOrder(List<Orderline> orderlines, Order order) {
		odao.addOrder(orderlines, order);
	}


	@Override
	public Order findOrderByOrderNumAndSurname(String orderNum, String surname) {
		return odao.findOrderByOrderNumAndSurname(orderNum, surname);
	}

	/**
	 * this method returns auto generated unique order number
	 */
	@Override
	public String getUniqueOrderNum() {
		return odao.getUniqueOrderNum();
	}

	/**
	 * this method calculates the grand total price of the order list
	 * @param orderList, Arraylist of orderline
	 * @return double grandTotal
	 */
	@Override
	public double getGrandTotal(List<Orderline> orderList) {
		return odao.getGrandTotal(orderList);
	}

	
	@Override
	public List<Order> findOutstandingOrders() {
		return odao.findOutstandingOrders();
	}

	@Override
	public Order findOrderByOrderNumber(String orderNumber) {
		return odao.findOrderByOrderNumber(orderNumber);
	}

	@Override
	public void updateOrderStatus(String orderNumber, String status) {
		odao.updateOrderStatus(orderNumber, status);
	}

}
