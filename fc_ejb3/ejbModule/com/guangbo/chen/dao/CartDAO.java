package com.guangbo.chen.dao;

import java.util.Collection;

import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;
/**
 * this is interface for CartDAO implementation class
 * @author guangbo
 */
public interface CartDAO {

	//Basic CRUD methods
	public void addToCart(Product p, int quantity);
	public void updateOrder(int quantity, int productId);
	public void deleteOrder(int productId);
	public Collection<Orderline> getOrderList();
	public double getGrandTotal();
}
