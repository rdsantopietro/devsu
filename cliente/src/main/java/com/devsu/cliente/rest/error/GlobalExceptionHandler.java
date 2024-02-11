package com.devsu.cliente.rest.error;

import com.devsu.cliente.enums.ErrorCodes;
import com.devsu.cliente.service.Error.MoreThanOneResultException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MoreThanOneResultException.class)
    public ResponseEntity<String> handleMoreThanOneResultException(MoreThanOneResultException ex) {
        return ResponseEntity.status(ErrorCodes.BUSQUEDA_MAS_DE_UN_ELEMENTO.getCODE()).body(ex.getMessage());
    }
}
