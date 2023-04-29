package com.amssolutions.prueba.service.adapter.exception;

/**
 * Product not found business exception.
 * 
 * @author obarcia
 */
public class ProductBusinessNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1639766350909140256L;
	
	public ProductBusinessNotFoundException() {
		super();
	}
	
	public ProductBusinessNotFoundException(Throwable cause) {
		super(cause);
	}

}
