package com.compra.business.exception;

/**
 * Created by marcelo.lima on 16/09/2016.
 * ¯\_(ツ)_/¯ KISS, YAGNI, DRY, SOLID.
 */
public class VendaNotCreateException extends RuntimeException {
    public VendaNotCreateException(String message) {
        super(message);
    }

    public VendaNotCreateException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
