package com.guangbo.chen.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.guangbo.chen.dao.OrderJpaDAO;
import com.guangbo.chen.dao.OrderJpaImpl;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;

/**
 * Session Bean implementation class OrderEjbDao
 * this class manages customer orderlines
 */
@Stateless(name = "OrderEjb", mappedName = "ejb/order")
@DeclareRoles({"orders","supplier"})
public class OrderBean implements OrderBeanRemote, OrderBeanLocal {
	@PersistenceContext
	private EntityManager em;
	private OrderJpaDAO odao;

	/**
	 * postConstructor to initalise the entity manager 
	 * before it has been created
	 */
	@PostConstruct
	public void init()
	{
		odao = new OrderJpaImpl(em);
	}
	
	/**
	 * this method add customer orders to the database
	 */
	@Override
	public void addOrder(List<Orderline> orderlines, Order order) {
		odao.addOrder(orderlines, order);
	}


	/**
	 * this method find specific order via order number and surname
	 * @param orderNum, String order number
	 * @param surname, String surname
	 * @return order, order object
	 */
	@Override
	public Order findOrderByOrderNumAndSurname(String orderNum, String surname) {
		return odao.findOrderByOrderNumAndSurname(orderNum, surname);
	}
	

	/**
	 * this method returns auto generated unique order number
	 * @return String unique order number
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

	
	//methods for admin
	/**
	 * this method retruns a list of order with status "PAID" and "ORDERED"
	 * @return list of order
	 */
	@Override
	@RolesAllowed("orders")
	public List<Order> findOutstandingOrders() {
		return odao.findOutstandingOrders();
	}

	/**
	 * this method returns a specific order according to the order number
	 * @return order object
	 */
	@Override
	@RolesAllowed("orders")
	public Order findOrderByOrderNumber(String orderNumber) {
		return odao.findOrderByOrderNumber(orderNumber);
	}
	
	
	/**
	 * this method update customer orderStatus via order number and status
	 * @param orderNumber, String order number
	 * @param status, String order status
	 */
	@Override
	@RolesAllowed("orders")
	public void updateOrderStatus(String orderNumber, String status) {
		odao.updateOrderStatus(orderNumber, status);
	}

	
	// methods for supplier
	/**
	 * this method returns a list of order with status "PAID" only
	 * @return list of order
	 */
	@Override
	@RolesAllowed("supplier")
	public List<Order> findPaidOrders() {
		return odao.findPaidOrders();
	}

	/**
	 * this method allows supplier to update order status
	 * @param orderNumber, String order number
	 * @param status, String order status
	 * @return boolean, true if updating successfully
	 */
	@Override
	@RolesAllowed("supplier")
	public boolean updatePaidOrder(String orderNumber, String status) {
		return odao.updatePaidOrder(orderNumber, status);
	}

}
