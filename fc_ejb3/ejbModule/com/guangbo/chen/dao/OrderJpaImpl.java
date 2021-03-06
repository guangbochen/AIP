package com.guangbo.chen.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

/**
 * Session Bean implementation class OrderEjbDao
 * this class manages the the CRUD methods that corresponds to the orders
 */
public class OrderJpaImpl implements OrderJpaDAO{
	private EntityManager em;
	private final static String defaultStatus = "ORDERED";
	private final static String prefixOfUniqueId = "guchen";
	private static String orderNumber = null;

	/**
	 *  special ejb3 constructor to set the entity manager.
	 *  @param EntityManager, container entity manager
	 */
    public OrderJpaImpl(EntityManager em) 
    {
    	this.em = em;
    }
    
    /**
     * this method persist order and related orderLines into the database
     * @param orderlines, list of order lines
     * @param order, order object
     */
	@Override
	public void addOrder(List<Orderline> orderlines, Order order) {
		try
		{
			//insert customer order and associated orderlines into the database
			order.setOrderNumber(generateOrderNum());
			order.setStatus(defaultStatus);
			order.setOrderlines(orderlines);
			em.persist(order);
			for(Orderline ol : orderlines)
			{
				ol.setOrder(order);
				em.persist(ol);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * this method finds a specific order by order number and surname
	 * @param orderNum, string order number
	 * @param surname, string surname
	 * @return order
	 */
	@Override
	public Order findOrderByOrderNumAndSurname(String orderNum, String surname) {
		Order order = new Order();
		try {
			order = (Order) em.createNamedQuery("order.viewOrder")
					.setParameter(1, orderNum)
					.setParameter(2, surname)
					.getSingleResult();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	/**
	 * this method returns auto generated unique order number
	 * @return order number, String order number
	 */
	@Override
	public String getUniqueOrderNum() {
		return orderNumber;
	}

	/**
	 * this method generates unique order number for each order
	 * @return order number
	 */
	private String generateOrderNum()
	{
		int orderId = 0;
		try 
		{
			Integer id = (Integer) em.createNamedQuery("order.getUniqueNum").getSingleResult();
			//if the database is empty set first unique order number as 1
			if(id == null)
				orderId = 1;
			else
				orderId =  id.intValue()+1;
			//converting int id to string and add prefix to order number
			String uniqueId = String.format("%04d", orderId);
			orderNumber = prefixOfUniqueId + uniqueId;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return orderNumber;
	}
	
	/**
	 * this method calculates the grand total price of the order list
	 * @param orderList, Arraylist of orderline
	 * @return double grandTotal
	 */
	@Override
	public double getGrandTotal(List<Orderline> orderList) {
		double grandTotal = 0.0;
		if(orderList != null)
		{
			for(Orderline ol : orderList)
			{
				grandTotal += ol.getLineTotal();
				grandTotal = Math.round(grandTotal*100.00)/100.00;
			}
		}
		return grandTotal;
	}

	
	/**
	 * this method returns a list of outstanding order with ordered and paid status
	 * @return orders, list of order
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOutstandingOrders() {
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = em.createNamedQuery("order.findOutstandingOrders")
					.setParameter(1, "ORDERED")
					.setParameter(2, "PAID")
					.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	
	/**
	 * this method returns an order by unique order number
	 * @return order
	 */
	@Override
	public Order findOrderByOrderNumber(String orderNumber) {
		Order order = new Order();
		try {
			order = (Order) em.createNamedQuery("order.findOrderByOrderNum")
					.setParameter(1, orderNumber)
					.getSingleResult();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return order;
	}

	/**
	 * this method update order status via order number and order status
	 * @param orderNumber, string order number
	 * @param status, string order status
	 */
	@Override
	public void updateOrderStatus(String orderNumber, String status) {
		try {
			Order order = (Order) em.createNamedQuery("order.findOrderByOrderNum")
					.setParameter(1, orderNumber)
					.getSingleResult();
			order.setStatus(status);
			em.merge(order);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * this method returns a list of orders with order status "PAID"
	 * @return orders, list of order
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findPaidOrders() {
		List<Order> orders = new ArrayList<Order>();
		try
		{
			orders = em.createNamedQuery("order.findPaidOrders")
					.setParameter(1, "PAID")
					.getResultList();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	/**
	 * this method update order status via order number and order status
	 * @param orderNumber, string order number
	 * @param status, string order status
	 * @return boolean, result of updating order
	 */
	@Override
	public boolean updatePaidOrder(String orderNumber, String status) {
		
		try {
			//if status is not set as "SENT", terminate updating and return false
			if(!status.equals("SENT") || orderNumber.equals("")) return false;
			
			//update order to status "SENT" according to order number
			Order order = (Order) em.createNamedQuery("order.findOrderByOrderNum")
					.setParameter(1, orderNumber)
					.getSingleResult();
			order.setStatus(status);
			em.merge(order);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
