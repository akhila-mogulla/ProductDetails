package com.dnb.ProductDetails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.ProductDetails.dto.Product;
import com.dnb.ProductDetails.exceptions.IdNotFoundException;
import com.dnb.ProductDetails.exceptions.UniqueIdNotFoundException;
import com.dnb.ProductDetails.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductServices {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);

	}

	@Override
	public Optional<Product> getProductById(String productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId);
	}

	@Override
	public Product updateProductById(String productId, Product product)
			throws IdNotFoundException, UniqueIdNotFoundException {
		Optional<Product> prod;
		if (productRepository.existsById(productId)) {
			if (productRepository.existsByProductName(product.getProductName())) {
				prod = productRepository.findById(productId);
				String name = prod.get().getProductName();
				if (name.equals(product.getProductName())) {
					return prod.get();
				} else
					return productRepository.save(product);
			} else
				return productRepository.save(product);
		}

		else
			throw new IdNotFoundException("No product id find to delete");

	}

	@Override
	public boolean getProductByProductName(String productName) {
		// TODO Auto-generated method stub
		return productRepository.existsByProductName(productName);
	}

	@Override
	public boolean deleteProductById(String productId) {
		// TODO Auto-generated method stub
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			return true;
		} else
			return false;

	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll();

	}

}
