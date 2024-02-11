package com.devsu.movimiento.service.error;

public class NonExistingCliente extends RuntimeException {

    static final String DEFAULT_MESSAGE = "No Existe El Cliente Buscado";

    public NonExistingCliente(String message) {
        super(message);
    }

    public NonExistingCliente(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistingCliente() {
        super(DEFAULT_MESSAGE);
    }
}

