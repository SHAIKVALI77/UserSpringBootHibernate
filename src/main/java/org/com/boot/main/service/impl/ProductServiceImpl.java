package org.com.boot.main.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.com.boot.main.daos.ProductDAO;
import org.com.boot.main.entities.Product;
import org.com.boot.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDAO;
	
	Product product;
	
	@Override
	public List<Product> getProduct() {
		
		return productDAO.getProduct();
	}


	@Override
	public Product find(String code) {
		
		List<Product> allProducts = productDAO.getProduct(); 
		  for (Product product : allProducts) 
		  { 
			  if (product.getCode().equalsIgnoreCase(code)) 
			  { 
				  return product; 
			    } 
			  }
		  
		  return product; 
		  }

		public void save(@Valid Product product) {
			
			productDAO.save(product);
		}
}
