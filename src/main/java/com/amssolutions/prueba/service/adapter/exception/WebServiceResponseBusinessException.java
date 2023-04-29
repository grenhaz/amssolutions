package com.amssolutions.prueba.service.adapter.exception;

/**
 * Web service response business exception.
 * 
 * @author obarcia
 */
public class WebServiceResponseBusinessException extends RuntimeException {

	private static final long serialVersionUID = -5637645365011708037L;
	
	public WebServiceResponseBusinessException() {
		super();
	}
	
	public WebServiceResponseBusinessException(String message) {
		super(message);
	}
	
	public WebServiceResponseBusinessException(Throwable cause) {
		super(cause);
	}

}
