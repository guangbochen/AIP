package com.guangbo.chen.ejb;

import java.util.Collection;
import javax.ejb.Remote;
import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;

@Remote
public interface CartBeanDAO {
	public void addToCart(Product p, int quantity);
	public Collection<Orderline> getOrderList();
}
