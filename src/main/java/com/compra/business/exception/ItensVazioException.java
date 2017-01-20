package com.compra.business.exception;

public class ItensVazioException extends RuntimeException{

	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ItensVazioException(String message) {
		        super(message);
		    }

		    public ItensVazioException(String message, Throwable throwable) {
		        super(message, throwable);
		    }
}
