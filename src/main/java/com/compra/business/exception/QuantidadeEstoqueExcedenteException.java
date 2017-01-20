package com.compra.business.exception;

public class QuantidadeEstoqueExcedenteException extends RuntimeException{

	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public QuantidadeEstoqueExcedenteException(String message) {
		        super(message);
		    }

		    public QuantidadeEstoqueExcedenteException(String message, Throwable throwable) {
		        super(message, throwable);
		    }
}
