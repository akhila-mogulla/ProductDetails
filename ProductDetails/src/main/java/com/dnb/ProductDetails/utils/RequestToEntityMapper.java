package com.dnb.ProductDetails.utils;

import org.springframework.stereotype.Component;

import com.dnb.ProductDetails.dto.Product;
import com.dnb.ProductDetails.payload.request.ProductRequest;

import lombok.Data;

@Component
@Data
public class RequestToEntityMapper {
	
	public Product getProductEntityObject(ProductRequest productRequest) {
		Product product = new Product();
		
		product.setProductName(productRequest.getProductName());
		product.setDescription(productRequest.getDescription());
		product.setProductCategory(productRequest.getProductCategory());
		product.setPrice(productRequest.getPrice());
		product.setProductExpiry(productRequest.getProductExpiry());
		return product;
	}

	
}
