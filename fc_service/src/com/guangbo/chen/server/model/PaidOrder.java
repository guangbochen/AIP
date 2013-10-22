package com.guangbo.chen.server.model;

import java.io.Serializable;

/**
 * this class is serializable java bean for supplier server
 * @author guangbo
 */
public class PaidOrder implements Serializable{
	
	private String orderNumber;
	private String surname;
	private int totalCount;
	private String status;
	private double grandTotal;
	
	/**
	 * constructor without fields
	 */
	public PaidOrder() {
		super();
	}
	
	/**
	 * @param orderNumber, string order number
	 * @param surname, string surname
	 * @param totalCount, int total count of quantities
	 * @param status, string order status
	 * @param grandTotal, double grand total of order
	 */
	public PaidOrder(String orderNumber, String surname, int totalCount,
			String status, double grandTotal) {
		super();
		this.orderNumber = orderNumber;
		this.surname = surname;
		this.totalCount = totalCount;
		this.status = status;
		this.grandTotal = grandTotal;
	}
	
	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the grandTotal
	 */
	public double getGrandTotal() {
		return grandTotal;
	}
	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
}
