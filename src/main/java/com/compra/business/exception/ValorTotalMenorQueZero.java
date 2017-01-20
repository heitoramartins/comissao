package com.compra.business.exception;

public class ValorTotalMenorQueZero extends RuntimeException{

	public ValorTotalMenorQueZero(String message) {
        super(message);
    }

    public ValorTotalMenorQueZero(String message, Throwable throwable) {
        super(message, throwable);
    }
}
