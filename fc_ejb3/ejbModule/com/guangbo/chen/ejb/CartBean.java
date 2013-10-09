package com.guangbo.chen.ejb;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateful;

import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;

/**
 * Session Bean implementation class CartBean
 */
@Stateful(mappedName = "ejb/cartBean")
public class CartBean implements CartBeanRemote, CartBeanLocal {
	private OrderBean odao ;
	private ArrayList<Orderline> orderList = new ArrayList<Orderline>();
	
	@Override
	public void addToCart(Product p, int quantity) {
		
		//initialize the bean if the cart session bean is empty
		if(orderList == null || orderList.isEmpty())
		{
			Orderline ol = new Orderline(p,quantity,p.getPrice()*quantity);
			orderList.add(ol);
		}
		else
		{
			Boolean newOrder = true;
			for(Orderline ol : orderList)
			{
				//update product quantity and lineTotal if order is already exist
				if(ol.getProduct().getId() == p.getId())
				{
					int totalQuantity = ol.getQuantity() + quantity;
					ol.setQuantity(totalQuantity);
					ol.setLineTotal(p.getPrice()*ol.getQuantity());
					newOrder = false;
					break;
				}
			}
			if(newOrder)
			{
				Orderline ol = new Orderline(p,quantity,p.getPrice()*quantity);
				orderList.add(ol);
			}
		}
		
	}

	@Override
	public Collection<Orderline> getOrderList() {
		for(Orderline ol : orderList)
		{
			System.out.println(ol.getProduct().getCategory());
		}
		return orderList;
	}

}
