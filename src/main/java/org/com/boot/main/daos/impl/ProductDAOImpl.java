package org.com.boot.main.daos.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.com.boot.main.daos.ProductDAO;
import org.com.boot.main.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	Session session;
	
	@Override
	public List<Product> getProduct() {
		session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Product.class);
		System.out.println("////////////////"+criteria);
		Root contactRoot = criteria.from(Product.class);
		criteria.select(contactRoot);
		
		return session.createQuery(criteria).getResultList();
	}


	@Override
	public void save(@Valid Product product) {
		try {
		session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction tx = session.beginTransaction();
		
		Serializable i = session.save(product);
		tx.commit();
		System.out.println("////////////////"+i);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

}
