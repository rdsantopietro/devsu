package com.devsu.movimiento.service;

import com.devsu.movimiento.service.error.NonExistingCuenta;

import java.math.BigDecimal;

public interface GetLastSaldoService {

    BigDecimal getLastSaldo(Long numeroCuneta) throws NonExistingCuenta;

}
