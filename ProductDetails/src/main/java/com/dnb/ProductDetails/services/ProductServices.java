package com.dnb.ProductDetails.services;

import java.util.List;
import java.util.Optional;

import com.dnb.ProductDetails.dto.Product;
import com.dnb.ProductDetails.exceptions.IdNotFoundException;
import com.dnb.ProductDetails.exceptions.UniqueIdNotFoundException;

public interface ProductServices {
	
	public Product createProduct(Product product) ;
	
	public Optional<Product> getProductById(String productId);
	
	public Product updateProductById(String productId, Product product) throws IdNotFoundException, UniqueIdNotFoundException;
	
	public  boolean deleteProductById(String productId);
	
	public List<Product> getAllProducts();
	
	//public Product getProductById(String productId);

	public boolean getProductByProductName(String productName);

}
