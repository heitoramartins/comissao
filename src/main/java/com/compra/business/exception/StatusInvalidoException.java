package com.compra.business.exception;

public class StatusInvalidoException extends RuntimeException{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatusInvalidoException(String message) {
	        super(message);
	    }

	    public StatusInvalidoException(String message, Throwable throwable) {
	        super(message, throwable);
	    }
	
}
