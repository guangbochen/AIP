package com.guangbo.chen.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.guangbo.chen.dao.CartDAO;
import com.guangbo.chen.dao.CartDAOImpl;
import com.guangbo.chen.dao.OrderJpaDAO;
import com.guangbo.chen.dao.OrderJpaImpl;
import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;

/**
 * Session Bean implementation class CartBean
 * this class manages customer orders
 */
@Stateful(mappedName = "ejb/cartBean")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CartBean implements CartBeanRemote, CartBeanLocal {
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	private OrderJpaDAO odao;
	private CartDAO cart;
	//private ArrayList<Orderline> orderList = new ArrayList<Orderline>();
	
	/**
	 * postConstructor to initalise the entity manager 
	 * before it has been created
	 */
	@PostConstruct
	public void init()
	{
		odao = new OrderJpaImpl(em);
		cart = new CartDAOImpl();
	}
	
	/**
	 * this method add customer orders to the database
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void addOrder(List<Orderline> orderlines, Order order) {
		odao.addOrder(orderlines, order);
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
	 * this method add customer ordering product to the cart
	 * @param p, product object
	 * @param quantity, int quantity
	 */
	@Override
	public void addToCart(Product p, int quantity) {
		cart.addToCart(p, quantity);
	}

	
	/**
	 * this method returns a list of orderlines
	 * @return orderList, list of orderlines
	 */
	@Override
	public Collection<Orderline> getOrderList() {
		return cart.getOrderList();
	}

	/**
	 * this method update orderline's quantity via product id
	 * @param quantity, int quantity
	 * @param productId, int product id
	 */
	@Override
	public void updateOrder(int quantity, int productId) {
		cart.updateOrder(quantity, productId);
	}

	/**
	 * this method returns the grandTotal price of the oder
	 * @return grandTotal, double grandTotal
	 */
	@Override
	public double getGrandTotal() {
		return cart.getGrandTotal();
	}

	
	/**
	 * this method deleted specific orderlines upon the product id
	 * @param productId, int product id
	 */
	@Override
	public void deleteOrder(int productId) {
		cart.deleteOrder(productId);
	}
	

}
