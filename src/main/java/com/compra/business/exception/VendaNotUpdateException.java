package com.compra.business.exception;

/**
 * Created by marcelo.lima on 16/09/2016.
 * ¯\_(ツ)_/¯ KISS, YAGNI, DRY, SOLID.
 */
public class VendaNotUpdateException extends RuntimeException {
    public VendaNotUpdateException(String message) {
        super(message);
    }

    public VendaNotUpdateException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
