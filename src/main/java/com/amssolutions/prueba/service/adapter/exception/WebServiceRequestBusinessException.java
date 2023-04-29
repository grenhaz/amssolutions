package com.amssolutions.prueba.service.adapter.exception;

/**
 * Web service request business exception.
 * 
 * @author obarcia
 */
public class WebServiceRequestBusinessException extends RuntimeException {

	private static final long serialVersionUID = -5637645365011708037L;
	
	public WebServiceRequestBusinessException() {
		super();
	}
	
	public WebServiceRequestBusinessException(String message) {
		super(message);
	}
	
	public WebServiceRequestBusinessException(Throwable cause) {
		super(cause);
	}

}
