package com.dnb.ProductDetails.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.ProductDetails.dto.Product;
import com.dnb.ProductDetails.exceptions.IdNotFoundException;
import com.dnb.ProductDetails.exceptions.UniqueIdNotFoundException;
import com.dnb.ProductDetails.payload.request.ProductRequest;
import com.dnb.ProductDetails.services.ProductServices;
import com.dnb.ProductDetails.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductServices productServices;

	@Autowired
	RequestToEntityMapper prod;

	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) throws IdNotFoundException {
		boolean account = productServices.deleteProductById(productId);
		 if(account) {
		return ResponseEntity.noContent().build();
		 }
		 else 
		 throw new IdNotFoundException("product id not available to delete");
	
}
	@GetMapping("/{productId}") 

	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) {
		Optional<Product> optional = productServices.getProductById(productId);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "Product id not found");
			map.put("HttpStatus", HttpStatus.NOT_FOUND + "");
			ResponseEntity<?> responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
			return responseEntity.ok(map);
		}
	}

	@GetMapping("/all") 

	public ResponseEntity<?> getAllProducts() {
		List<Product> optional = productServices.getAllProducts();
		return ResponseEntity.ok(optional);

	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable("productId") String productId, @Valid @RequestBody Product product) throws IdNotFoundException, UniqueIdNotFoundException {
	
		 product.setProductId(productId);
			return new ResponseEntity(productServices.updateProductById(productId, product),HttpStatus.CREATED);
		
			//throw new IdNotFoundException("Id not found ");
	
	}

	@PostMapping("/create") 
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest product)  {
		Product product2 = prod.getProductEntityObject(product);
		Product product3 = productServices.createProduct(product2);
		return new ResponseEntity(product3, HttpStatus.CREATED);
		// return ResponseEntity.ok(mapper.getAccountEntityObject(account));

	}

}
