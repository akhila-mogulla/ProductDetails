package com.dnb.ProductDetails.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.ProductDetails.dto.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
	public boolean existsByProductName(String productName);

}
