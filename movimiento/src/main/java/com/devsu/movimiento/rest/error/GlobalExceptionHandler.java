package com.devsu.movimiento.rest.error;

import com.devsu.movimiento.enums.ErrorCodes;
import com.devsu.movimiento.service.error.NonExistingCuenta;
import com.devsu.movimiento.service.error.MoreThanOneResultException;
import com.devsu.movimiento.service.error.NonExistingCliente;
import com.devsu.movimiento.service.error.SaldoInsuficienteException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.NotContextException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> handleSaldoInsuficienteException(SaldoInsuficienteException ex) {
        return ResponseEntity.status(ErrorCodes.SALDO_INSUFICIENTE.getCODE()).body(ex.getMessage());
    }

    @ExceptionHandler(NonExistingCuenta.class)
    public ResponseEntity<String> handleCuentaInexistenteException(NonExistingCuenta ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NotContextException.class)
    public ResponseEntity<String> handleNotContextException(NotContextException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(MoreThanOneResultException.class)
    public ResponseEntity<String> hanleMoreThanOneResultException(MoreThanOneResultException ex){
        return ResponseEntity.status(ErrorCodes.BUSQUEDA_MAS_DE_UN_ELEMENTO.getCODE()).body(ex.getMessage());
    }

    @ExceptionHandler(NonExistingCliente.class)
    public ResponseEntity<String> handleNonExistingCliente(NonExistingCliente ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
