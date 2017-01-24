package com.compra.business.exception;

/**
 * Created by marcelo.lima on 16/09/2016.
 * ¯\_(ツ)_/¯ KISS, YAGNI, DRY, SOLID.
 */
public class PedidoNotCreateException extends RuntimeException {
    public PedidoNotCreateException(String message) {
        super(message);
    }

    public PedidoNotCreateException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
