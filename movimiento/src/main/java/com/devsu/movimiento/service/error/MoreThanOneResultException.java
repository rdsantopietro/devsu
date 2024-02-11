package com.devsu.movimiento.service.error;

public class MoreThanOneResultException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Mas De Un Elemento Encontrado";


    public MoreThanOneResultException(String message) {
        super(message);
    }

    public MoreThanOneResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoreThanOneResultException() {
        super(DEFAULT_MESSAGE);
    }
}

