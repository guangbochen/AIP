package com.guangbo.chen.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.Stateful;

import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;

/**
 * Session Bean implementation class CartBean
 */
@Stateful(mappedName = "ejb/cartBean")
public class CartBean implements CartBeanRemote, CartBeanLocal {
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
		return orderList;
	}

	@Override
	public void updateOrder(int quantity, int productId) {
		if(orderList != null || !orderList.isEmpty())
		{
			for(Orderline ol : orderList)
			{
				//update product quantity via product id
				if(ol.getProduct().getId() == productId)
				{
					//update lineTotal
					ol.setQuantity(quantity);
					double lineTotal = quantity*ol.getProduct().getPrice();
					ol.setLineTotal(lineTotal);
					break;
				}
			}
		}
	}

	@Override
	public double getGrandTotal() {
		double grandTotal = 0.00;
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

	@Override
	public void deleteOrder(int productId) {
		for (Iterator<Orderline> it = orderList.iterator(); it.hasNext(); )
		{
			Orderline ol = (Orderline) it.next();  
			if(ol.getProduct().getId() == productId )
			{
				it.remove();
			}
		}
	}
	

}
