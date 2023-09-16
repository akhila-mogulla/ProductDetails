package com.dnb.ProductDetails.exceptions;

public class IdNotFoundException extends Exception {
	
	public IdNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+super.getMessage();
	}

}
