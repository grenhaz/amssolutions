package com.amssolutions.prueba.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amssolutions.prueba.api.dto.ResponseError;
import com.amssolutions.prueba.exception.ProductNotFoundException;
import com.amssolutions.prueba.exception.WebServiceException;

import jakarta.validation.ConstraintViolationException;

/**
 * Api controller handler.
 * 
 * @author obarcia
 */
@ControllerAdvice
public class RestResponseExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	ResponseEntity<Void> productNotFoundHandler(final ProductNotFoundException ex) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(WebServiceException.class)
	ResponseEntity<Void> webServiceErrorHandler(final WebServiceException ex) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<ResponseError> badRequestErrorHandler(final ConstraintViolationException ex) {
		ResponseError error = new ResponseError();
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}
