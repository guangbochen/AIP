package com.guangbo.chen.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import com.guangbo.chen.jpa.Product;

/**
 * this is remote interface for Product EJB bean
 * @author guangbo
 */
@Remote
public interface ProductBeanRemote {

	//searching products
	public List<Product> findAll();
	public List<Product> findAllByPagination(int offset, int noOfRecords);
	public List<Product> findAllByCategory(String category);
	public List<Product> findAllCategoryByPagination(String category, int offset, int noOfRecords);
	public List<String> findAllCategory();
}
