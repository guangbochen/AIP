package com.guangbo.chen.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.guangbo.chen.jpa.Orderline;
import com.guangbo.chen.jpa.Product;


/**
 * this class implements CartDAO and manages
 * relevant action of the shopping cartBean
 * @author guangbo
 */
public class CartDAOImpl implements CartDAO{
	private ArrayList<Orderline> orderList = new ArrayList<Orderline>();

	/**
	 * this method add customer ordering product to the cart
	 * @param p, product object
	 * @param quantity, int quantity
	 */
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

	
	/**
	 * this method returns a list of orderlines
	 * @return orderList, list of orderlines
	 */
	@Override
	public Collection<Orderline> getOrderList() {
		return orderList;
	}

	/**
	 * this method update orderline's quantity via product id
	 * @param quantity, int quantity
	 * @param productId, int product id
	 */
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

	/**
	 * this method returns the grandTotal price of the oder
	 * @return grandTotal, double grandTotal
	 */
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

	
	/**
	 * this method deleted specific orderlines upon the product id
	 * @param productId, int product id
	 */
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
