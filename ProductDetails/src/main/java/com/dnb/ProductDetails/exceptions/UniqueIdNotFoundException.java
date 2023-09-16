package com.dnb.ProductDetails.exceptions;

public class UniqueIdNotFoundException extends Exception {
	public UniqueIdNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+super.getMessage();
	}


}
