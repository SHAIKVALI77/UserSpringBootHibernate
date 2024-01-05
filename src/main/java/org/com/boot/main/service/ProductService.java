package org.com.boot.main.service;

import java.util.List;

import javax.validation.Valid;

import org.com.boot.main.entities.Product;

public interface ProductService {

	List<Product> getProduct();

	Product find(String code);

	void save(@Valid Product product);
}
