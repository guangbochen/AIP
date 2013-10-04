package com.guangbo.chen.jpa;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderlines")
public class Orderline implements Serializable {

	private int id;
	private Product product;
	private Order order;
	private int quantity;
	private double lineTotal;
	
	/**
	 * Orderline constructor 
	 */
	public Orderline() {
		super();
	}
	
	/**
	 * Orderline constructor with instance fields
	 */
	public Orderline(Product product, int quantity, double lineTotal) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.lineTotal = lineTotal;
	}
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the product
	 */
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="product_id", nullable = false)
	public Product getProduct() {
		return product;
	}
	
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * @return the order
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "order_id", nullable = false)
	public Order getOrder() {
		return order;
	}
	
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order orderx) {
		this.order = orderx;
	}
	
	/**
	 * @return the quantity
	 */
	@Column(name = "quantity", unique = true, nullable = false, columnDefinition = "INT(10)")
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return the lineTotal
	 */
	@Column(name = "lineTotal", unique = true, nullable = false, columnDefinition = "Double(7,2)")
	public double getLineTotal() {
		return lineTotal;
	}
	
	/**
	 * @param lineTotal the lineTotal to set
	 */
	public void setLineTotal(double lineTotal) {
		lineTotal = Math.round(lineTotal*100.0)/100.0;
		this.lineTotal = lineTotal;
	}

}
