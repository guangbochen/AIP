package com.guangbo.chen.dao;

import java.util.List;
import com.guangbo.chen.jpa.Product;


/**
 * this is Product JPA implementation interface 
 * @author guangbo
 */
public interface ProductJpaDAO {

	//Basic searching facets
	public List<Product> findAll();
	public List<Product> findAllByPagination(int offset, int noOfRecords);
	public List<Product> findAllByCategory(String category);
	public List<Product> findAllCategoryByPagination(String category, int offset, int noOfRecords);
	public List<String> findAllCategory();
}
