package org.com.boot.main.daos;

import java.util.List;

import javax.validation.Valid;

import org.com.boot.main.entities.Product;

public interface ProductDAO {

	public List<Product> getProduct();

	public void save(@Valid Product product);
}
