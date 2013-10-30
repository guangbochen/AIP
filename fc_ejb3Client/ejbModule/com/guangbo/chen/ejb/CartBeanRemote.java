package com.guangbo.chen.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.guangbo.chen.jpa.Order;
import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;

/**
 * this is remote interface of CartBean EJB bean
 * @author guangbo
 */
@Remote
public interface CartBeanRemote {
	
	//Basic CRUD
	public void addToCart(Product p, int quantity);
	public void updateOrder(int quantity, int productId);
	public void deleteOrder(int productId);
	public void addOrder(List<Orderline> orderlines, Order order);
	public String getUniqueOrderNum();
	
	//searching methods
	public Collection<Orderline> getOrderList();
	public double getGrandTotal();
}
