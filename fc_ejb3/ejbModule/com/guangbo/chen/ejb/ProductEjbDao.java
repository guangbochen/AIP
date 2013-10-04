package com.guangbo.chen.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.guangbo.chen.dao.ProductJpaDAO;
import com.guangbo.chen.dao.ProductJpaImple;
import com.guangbo.chen.jpa.Product;

/**
 * Session Bean implementation class ProductEjbDao
 */
@Stateless(name = "productEjb", mappedName = "ejb/product")
public class ProductEjbDao implements ProductDAO, ProductDAOLocal {
	@PersistenceContext
	private EntityManager em;
	private ProductJpaDAO pdao;

	@Override
	public List<Product> findAll() {
		pdao = new ProductJpaImple(em);
		return pdao.findAll();
	}

	@Override
	public List<Product> findAllByPagination(int offset, int noOfRecords) {
		pdao = new ProductJpaImple(em);
		List<Product> products = pdao.findAllByPagination(offset, noOfRecords);
		return products;
	}

	@Override
	public List<Product> findAllByCategory(String category) {
		pdao = new ProductJpaImple(em);
		List<Product> products = pdao.findAllByCategory(category);
		return products;
	}

	@Override
	public List<Product> findAllCategoryByPagination(String category, int offset, int noOfRecords) {
		pdao = new ProductJpaImple(em);
		List<Product> products = pdao.findAllCategoryByPagination(category, offset, noOfRecords);
		return products;
	}
	
	/**
	 * this method find all the category name
	 * @return categoryList, list of category
	 */
	@Override
	public List<String> findAllCategory() {
		pdao = new ProductJpaImple(em);
		List<String> categoryList = pdao.findAllCategory();
		return categoryList;
	}

}
