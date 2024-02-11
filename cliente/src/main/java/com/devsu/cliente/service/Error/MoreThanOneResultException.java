package com.devsu.cliente.service.Error;

public class MoreThanOneResultException extends RuntimeException {

    public MoreThanOneResultException(String message) {
        super(message);
    }

    public MoreThanOneResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoreThanOneResultException() {

    }
}

