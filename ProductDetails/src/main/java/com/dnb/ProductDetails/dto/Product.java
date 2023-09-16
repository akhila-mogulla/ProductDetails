package com.dnb.ProductDetails.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dnb.ProductDetails.utils.CustomProductIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Product {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.UUID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@GenericGenerator(name = "product_seq", strategy = "com.dnb.ProductDetails.utils.CustomProductIdGenerator",
	parameters = {@Parameter(name=CustomProductIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name=CustomProductIdGenerator.VALUE_PREFIX_PARAMETER, value = "Pro_"),
			@Parameter(name=CustomProductIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
			})
	String productId;
	@Column(unique = true)
	String productName;
	String productCategory;
	String description;
	double price;
	String productExpiry;
	

}
