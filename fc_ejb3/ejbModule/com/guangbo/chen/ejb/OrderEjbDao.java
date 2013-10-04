package com.guangbo.chen.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

/**
 * Session Bean implementation class OrderEjbDao
 */
@Stateless(name = "OrderEjb", mappedName = "ejb/order")
public class OrderEjbDao implements OrderDAO, OrderDAOLocal {
	@PersistenceContext
	private EntityManager em;
	private final static String defaultStatus = "ORDERED";
	private final static String prefixOfUniqueId = "guchen";
	private static String orderNumber = null;

	/**
	 * default constructor
	 */
    public OrderEjbDao() { 
    }

	@Override
	public void addOrder(ArrayList<Orderline> orderlines, Order order) {
		try
		{
			//insert customer order and associated orderlines into the database
			order.setOrderNumber(generateOrderNum());
			order.setStatus(defaultStatus);
			order.setOrderLines(orderlines);
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
}
