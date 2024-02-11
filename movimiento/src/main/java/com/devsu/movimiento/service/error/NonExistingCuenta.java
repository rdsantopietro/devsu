package com.devsu.movimiento.service.error;

public class NonExistingCuenta extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "No se ha podido econtrar la cuenta buscada";

    public NonExistingCuenta(String message) {
        super(message);
    }

    public NonExistingCuenta(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistingCuenta() {
        super(DEFAULT_MESSAGE);
    }
}

