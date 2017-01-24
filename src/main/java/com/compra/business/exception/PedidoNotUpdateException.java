package com.compra.business.exception;

/**
 * Created by marcelo.lima on 16/09/2016.
 * ¯\_(ツ)_/¯ KISS, YAGNI, DRY, SOLID.
 */
public class PedidoNotUpdateException extends RuntimeException {
    public PedidoNotUpdateException(String message) {
        super(message);
    }

    public PedidoNotUpdateException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
