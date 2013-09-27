package com.guangbo.chen.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.guangbo.chen.jpa.Product;

/**
 * Session Bean implementation class ProductEjbDao
 */
@Stateless(name = "productEjb", mappedName = "ejb/product")
public class ProductEjbDao implements ProductDAO, ProductDAOLocal {
	@PersistenceContext
	private EntityManager em;

    public ProductEjbDao() {
    	
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
		finally{
			em.clear();
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
		finally{
			em.clear();
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAllByCategory(String category) {
		List<Product> products = null;
		try {
			products = em.createNamedQuery("product.findAllByCategory")
					.setParameter(1, category)
					.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			em.clear();
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAllCategoryByPagination(String category, int offset,
			int noOfRecords) {
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
		finally{
			em.clear();
		}
		return products;
	}
	
	/**
	 * this method find all the category name
	 * @return categoryList, list of category
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllCategory() {
		List<String> categoryList = null;
		try {
			categoryList = em.createNamedQuery("product.findAllCategory").getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.clear();
		}
		return categoryList;
	}

}
