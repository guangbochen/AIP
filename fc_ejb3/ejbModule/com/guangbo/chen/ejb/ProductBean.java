package com.guangbo.chen.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.guangbo.chen.dao.ProductJpaDAO;
import com.guangbo.chen.dao.ProductJpaImple;
import com.guangbo.chen.jpa.Product;

/**
 * Session Bean implementation class ProductEjbDao
 * this class manages the searching methods of products
 */
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Stateless(name = "productEjb", mappedName = "ejb/product")
public class ProductBean implements ProductBeanRemote, ProductBeanLocal {
	@PersistenceContext
	private EntityManager em;
	private ProductJpaDAO pdao;
	
	/**
	 * postConstructor to initalise the entity manager 
	 * before it has been created
	 */
	@PostConstruct
	public void init()
	{
		pdao = new ProductJpaImple(em);
	}

	/**
	 * this method returns a list of products
	 * @return list of products
	 */
	@Override
	public List<Product> findAll() {
		return pdao.findAll();
	}

	/**
	 * this method returns a list of products via pagination
	 * @param offset , int id of first product
	 * @param noOfRecords, int totoal number of records
	 * @return list of products
	 */
	@Override
	public List<Product> findAllByPagination(int offset, int noOfRecords) {
		return pdao.findAllByPagination(offset, noOfRecords);
	}

	/**
	 * this method returns a list of products by category
	 * @param category, String product category
	 * @return list of products
	 */
	@Override
	public List<Product> findAllByCategory(String category) {
		return pdao.findAllByCategory(category);
	}

	/**
	 * this method returns a list of products by category in pagination
	 * @param category, String product category
	 * @param offset,  int id of first product
	 * @param noOfRecords, int totoal number of records
	 * @return list of products
	 */
	@Override
	public List<Product> findAllCategoryByPagination(String category, int offset, int noOfRecords) {
		return pdao.findAllCategoryByPagination(category, offset, noOfRecords);
	}
	
	/**
	 * this method find all the category name
	 * @return categoryList, list of category
	 */
	@Override
	public List<String> findAllCategory() {
		return pdao.findAllCategory();
	}

}
