package com.guangbo.chen.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.guangbo.chen.jpa.Product;

/**
 * Session Bean implementation class ProductEjbDao
 * this class manages the searching methods that corresponds to the products
 */
public class ProductJpaImple implements ProductJpaDAO {
	private EntityManager em;

	/**
	 *  special ejb3 constructor to set the entity manager.
	 *  @param EntityManager, container entity manager
	 */
    public ProductJpaImple(EntityManager em) 
    {
    	this.em = em;
    }

    /**
     * this method returns a list of of all the products
     * @return products, list of products
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		
		List<Product> products = null;
		try
		{
			products = em.createNamedQuery("product.findAll").getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}

    /**
     * this method returns a list of products via pagination
     * @return products, list of products
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAllByPagination(int offset, int noOfRecords) {
		
		List<Product> products = null;
		try
		{
			products = em.createNamedQuery("product.findAll")
					.setFirstResult(offset)
					.setMaxResults(noOfRecords)
					.getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}

    /**
     * this method returns a list of products via category
     * @return products, list of products
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAllByCategory(String category) {
		
		List<Product> products = null;
		try
		{
			products = em.createNamedQuery("product.findAllByCategory")
				.setParameter(1, category)
				.getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}

    /**
     * this method returns a list of products via category in pagination
     * @return products, list of products
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAllCategoryByPagination(String category, int offset, int noOfRecords) {
		
		List<Product> products = null;
		try
		{
			products = em.createNamedQuery("product.findAllByCategory")
				.setParameter(1, category)
				.setFirstResult(offset)
				.setMaxResults(noOfRecords)
				.getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}
	
	/**
	 * this method return a list of available products category 
	 * @return categoryList, list of category
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllCategory() {
		
		List<String> categoryList = null;
		try
		{
			categoryList = em.createNamedQuery("product.findAllCategory").getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return categoryList;
	}

}
