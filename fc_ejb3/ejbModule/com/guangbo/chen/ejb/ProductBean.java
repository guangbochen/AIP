package com.guangbo.chen.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.service.spi.InjectService;

import com.guangbo.chen.dao.ProductJpaDAO;
import com.guangbo.chen.dao.ProductJpaImple;
import com.guangbo.chen.jpa.Product;

/**
 * Session Bean implementation class ProductEjbDao
 */
@Stateless(name = "productEjb", mappedName = "ejb/product")
public class ProductBean implements ProductBeanRemote, ProductBeanLocal {
	@PersistenceContext
	private EntityManager em;
	private ProductJpaDAO pdao;
	
	@PostConstruct
	public void init()
	{
		pdao = new ProductJpaImple(em);
	}

	@Override
	public List<Product> findAll() {
		return pdao.findAll();
	}

	@Override
	public List<Product> findAllByPagination(int offset, int noOfRecords) {
		return pdao.findAllByPagination(offset, noOfRecords);
	}

	@Override
	public List<Product> findAllByCategory(String category) {
		return pdao.findAllByCategory(category);
	}

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
