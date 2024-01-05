package org.com.boot.main.controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.com.boot.main.entities.Product;
import org.com.boot.main.model.Item;
import org.com.boot.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ProductController {

	@Autowired
	ProductService productService;
	List<Item> cart;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> userDetails() {
        
		List<Product> userDetails = productService.getProduct();
		return new ResponseEntity<List<Product>>(userDetails, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public String getView() {
		return "welcome";
	}
	
	@RequestMapping(value = "/addProducts", method = RequestMethod.GET) 
	public ModelAndView addProducts(Product product) {
	  
	  return new ModelAndView("NewProduct","product",new Product()); 
	  
	 }
	  	
	@RequestMapping(value = "/added", method = RequestMethod.POST)
    public ModelAndView submit(@Valid @ModelAttribute("product")Product product, 
    	BindingResult result, ModelMap model) {
      
        model.addAttribute("name", product.getName());
        model.addAttribute("code", product.getCode());
        model.addAttribute("price", product.getPrice());
        Date date = new Date();
        
        String strDate = DateFormat.getDateInstance().format(date);
        product.setCreateDate(date);
        
        productService.save(product);
        List<Product> allProducts = productService.getProduct();
        
        return new ModelAndView("allProducts", "product", allProducts);
    }
	
	@RequestMapping(value = "/index.jsp", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		
		List<Product> prod = productService.getProduct();
		return new ModelAndView("index","product",prod);
	}
	
	@RequestMapping(value = "/listOfProducts", method = RequestMethod.GET)
	public ModelAndView employees() {
		List<Product> allProducts = productService.getProduct();
		return new ModelAndView("allProducts", "product", allProducts);

	}
	
	@RequestMapping(value = "buy/{code}", method = RequestMethod.GET)
	public ModelAndView buy(@PathVariable("code") String code, HttpSession session) {
		
		//ProductModel productModel = new ProductModel();
		
		if (session.getAttribute("cart") == null) {
			cart = new ArrayList<Item>();
			cart.add(new Item(productService.find(code), 1));
			session.setAttribute("cart", cart);
		} else {
			@SuppressWarnings("unchecked")
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = this.exists(code, cart);
			if (index == -1) {
				cart.add(new Item(productService.find(code), 1));
			} else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}
		//new ModelAndView("allProducts", "product", allProducts)
		return new ModelAndView("cart", "cart", cart);//"redirect:/cart";
	}
	
	@RequestMapping(value = "remove/{code}", method = RequestMethod.GET)
	@SuppressWarnings("unchecked")
	public ModelAndView remove(@PathVariable("code") String code, HttpSession session) {
		//ProductModel productModel = new ProductModel();
		
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = this.exists(code, cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		return new ModelAndView("cart", "cart", cart);
	}
	private int exists(String id, List<Item> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getCode().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}	
}
