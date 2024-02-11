package com.devsu.movimiento.service;

import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.dto.CreateMovimientoDTO;
import com.devsu.movimiento.service.error.NonExistingCuenta;
import com.devsu.movimiento.service.error.SaldoInsuficienteException;


public interface ApplyMovimientoService {


    Movimiento applyMovimiento(CreateMovimientoDTO createMovimientoDTO) throws SaldoInsuficienteException, NonExistingCuenta;

}
