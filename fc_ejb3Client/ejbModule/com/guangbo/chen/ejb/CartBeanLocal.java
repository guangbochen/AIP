package com.guangbo.chen.ejb;

import java.util.Collection;
import javax.ejb.Local;
import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;

/**
 * this is local interface of CartBean EJB bean
 * @author guangbo
 */
@Local
public interface CartBeanLocal {

	//Basic CRUD
	public void addToCart(Product p, int quantity);
	public void updateOrder(int quantity, int productId);
	public void deleteOrder(int productId);
	
	//searching methods
	public Collection<Orderline> getOrderList();
	public double getGrandTotal();
}
