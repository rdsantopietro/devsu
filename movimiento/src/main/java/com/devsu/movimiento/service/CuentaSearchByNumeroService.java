package com.devsu.movimiento.service;

import com.devsu.movimiento.domain.Cuenta;

import java.util.Optional;

public interface CuentaSearchByNumeroService {
    Optional<Cuenta> searchByNumeroCuenta(Long numeroCuenta) ;
}
