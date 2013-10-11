package com.guangbo.chen.ejb;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;

@Remote
public interface CartBeanRemote {
	
	//Basic CRUD
	public void addToCart(Product p, int quantity);
	public void updateOrder(int quantity, int productId);
	public void deleteOrder(int productId);
	
	//searching methods
	public Collection<Orderline> getOrderList();
	public double getGrandTotal();
}
