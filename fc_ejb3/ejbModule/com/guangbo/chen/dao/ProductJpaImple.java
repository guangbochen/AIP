package com.guangbo.chen.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.guangbo.chen.jpa.Product;

/**
 * Session Bean implementation class ProductEjbDao
 */
public class ProductJpaImple implements ProductJpaDAO {
	private EntityManager em;

	/**
	 *  special ejb3 constructor to set the entity manager.
	 */
    public ProductJpaImple(EntityManager em) 
    {
    	this.em = em;
    }
    
    public void setEntityManager(EntityManager em)
    {
    	this.em = em;
    }

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
