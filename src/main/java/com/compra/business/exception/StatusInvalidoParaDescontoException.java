package com.compra.business.exception;

public class StatusInvalidoParaDescontoException extends RuntimeException{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatusInvalidoParaDescontoException(String message) {
	        super(message);
	    }

	    public StatusInvalidoParaDescontoException(String message, Throwable throwable) {
	        super(message, throwable);
	    }
	
}
