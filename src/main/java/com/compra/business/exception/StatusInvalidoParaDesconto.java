package com.compra.business.exception;

public class StatusInvalidoParaDesconto extends RuntimeException{

	public StatusInvalidoParaDesconto(String message) {
        super(message);
    }

    public StatusInvalidoParaDesconto(String message, Throwable throwable) {
        super(message, throwable);
    }
}
