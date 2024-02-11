package com.devsu.movimiento.service;

import com.devsu.movimiento.domain.Cuenta;

import java.util.Optional;

public interface CuentaSearchByClienteIdService {
    Optional<Cuenta> searchByClienteId(Long numeroCuenta) ;
}
