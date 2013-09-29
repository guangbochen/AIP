package com.guangbo.chen.jpa;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@NamedQueries({
	@NamedQuery(name = "product.findAll", query = "Select p From Product p"),
	@NamedQuery(name = "product.findAllCategory", query = "Select p.category From Product p Group By p.category"),
	@NamedQuery(name = "product.findAllByCategory", query = "SELECT p FROM Product p WHERE p.category LIKE ?1")
})
@Entity
@Table(name="products")
public class Product implements Serializable{
	
	private int id;
	private String Category;
	private String code;
	private String description;
	private double price;
	
	public Product() {
		super();
	}
	
	
	public Product(int id, String category, String code, String description, double price) {
		super();
		this.id = id;
		this.Category = category;
		this.code = code;
		this.description = description;
		this.price = price;
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
	 * @return the category
	 */
	@Column(name = "category", unique = true, nullable = false, length = 20)
	public String getCategory() {
		return Category;
	}
	
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		Category = category;
	}
	
	/**
	 * @return the code
	 */
	@Column(name = "code", unique = true, nullable = false, length = 20)
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the description
	 */
	@Column(name = "description", unique = true, nullable = false, length = 200)
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the price
	 */
	@Column(name = "price", unique = true, nullable = false, columnDefinition="Double(7,2)")
	public double getPrice() {
		return price;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
