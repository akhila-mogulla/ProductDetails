package com.dnb.ProductDetails.payload.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {
	
	@NotBlank(message = "product name should not be empty.")
	@Column(unique = true)
	String productName;
	@NotBlank(message = "product na should not be empty.")
	String productCategory;
	@NotEmpty(message = "product description should not be empty.")
	String description;
	@Min(value = 0,message="value should not be negative")
	double price;
	@NotNull(message = "expiry cannot be null." )
	String productExpiry;
	

}
