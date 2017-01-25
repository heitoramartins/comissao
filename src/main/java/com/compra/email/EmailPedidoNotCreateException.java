package com.compra.email;

/**
 * Created by marcelo.lima on 16/09/2016.
 * ¯\_(ツ)_/¯ KISS, YAGNI, DRY, SOLID.
 */
public class EmailPedidoNotCreateException extends RuntimeException {
    public EmailPedidoNotCreateException(String message) {
        super(message);
    }

    public EmailPedidoNotCreateException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
