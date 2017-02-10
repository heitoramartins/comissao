package com.compra.business.exception;

/**
 * Created by marcelo.lima on 16/09/2016.
 * ¯\_(ツ)_/¯ KISS, YAGNI, DRY, SOLID.
 */
public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(String message) {
        super(message);
    }

    public PedidoNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
