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

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="product_id", nullable = false)
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@Column(name = "quantity", unique = true, nullable = false, columnDefinition = "INT(10)")
	private int quantity;
	
	@Column(name = "lineTotal", unique = true, nullable = false, columnDefinition = "Double(7,2)")
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
	public Order getOrder() {
		return order;
	}
	
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
	/**
	 * @return the quantity
	 */
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
