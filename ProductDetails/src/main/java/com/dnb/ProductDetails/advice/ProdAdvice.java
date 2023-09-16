package com.dnb.ProductDetails.advice;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dnb.ProductDetails.exceptions.IdNotFoundException;

@ControllerAdvice
public class ProdAdvice {
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Id Provided")
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> IdNotFoundException(IdNotFoundException e){
		Map<String, String> map = new HashMap<>();
		map.put("message", "Product id not available.");
		map.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		ResponseEntity<?> responseEntity = new ResponseEntity(map,HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, HttpRequest request) {
		
		Map<String, Object> responseBody = new LinkedHashMap<>();
		responseBody.put("timestamp", LocalDate.now());
		responseBody.put("status", status.value());
		
		List<String> errors = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(x -> x.getField())
				.collect(Collectors.toList());
		//responseBody.put("error", errors);
		
		List<String> errors1 = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		responseBody.put("error", errors);
		
		Map<String, String> err = new LinkedHashMap<>();
		for(int i=0;i<errors.size();i++) {
			err.put(errors.get(i), errors1.get(i));
			
		}
		responseBody.put("error", err);
		return new ResponseEntity(responseBody, status);

	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> parentException(Exception e){
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("message", e.getMessage());
		map1.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		ResponseEntity<?> responseEntity = new ResponseEntity(map1,HttpStatus.NOT_FOUND);
		return responseEntity;
	}

}
