package com.dnb.ProductDetails.advice;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map<String, Object> responseBody = new LinkedHashMap<String, Object>();
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
		
		// TODO Auto-generated method stub
		return new ResponseEntity<>(responseBody,headers,status);
		
	}

}

