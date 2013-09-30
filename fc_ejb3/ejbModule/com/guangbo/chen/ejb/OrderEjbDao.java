package com.guangbo.chen.ejb;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

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
	public double getGrandTotal(ArrayList<Orderline> orderList) {
		double grandTotal = 0.0;
		if(orderList != null)
		{
			for(Orderline ol : orderList)
			{
				grandTotal += ol.getLineTotal();
			}
		}
		return grandTotal;
	}

	@Override
	public void addOrder(ArrayList<Orderline> orderList, Order order) {
		try
		{
			//insert customer order and associated orderlines into the database
			order.setOrderNumber(generateOrderNum());
			order.setStatus(defaultStatus);
			em.persist(order);
			for(Orderline ol : orderList)
			{
				ol.setOrder(order);
				ol.setProduct(ol.getProduct());
				order.getOrderLines().add(ol);
				em.persist(ol);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			em.clear();
		}
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

}
