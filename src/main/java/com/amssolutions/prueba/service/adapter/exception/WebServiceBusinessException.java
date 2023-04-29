package com.amssolutions.prueba.service.adapter.exception;

/**
 * Web service business exception.
 * 
 * @author obarcia
 */
public class WebServiceBusinessException extends RuntimeException {

	private static final long serialVersionUID = -5637645365011708037L;
	
	public WebServiceBusinessException() {
		super();
	}
	
	public WebServiceBusinessException(String message) {
		super(message);
	}
	
	public WebServiceBusinessException(Throwable cause) {
		super(cause);
	}

}
