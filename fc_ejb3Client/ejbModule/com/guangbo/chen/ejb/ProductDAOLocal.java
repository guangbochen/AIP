package com.guangbo.chen.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import com.guangbo.chen.jpa.Product;

@Local
public interface ProductDAOLocal {
	//searching products
	public List<Product> findAll();
	public List<Product> findAllByPagination(int offset, int noOfRecords);
	public List<Product> findAllByCategory(String category);
	public List<Product> findAllCategoryByPagination(String category, int offset, int noOfRecords);
	public List<String> findAllCategory();
}
