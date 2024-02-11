package com.devsu.movimiento.service;

import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.dto.CreateMovimientoDTO;
import org.springframework.beans.factory.annotation.Autowired;

public interface SaldoValidator {
    Boolean isValidSaldo(CreateMovimientoDTO movimiento);
}
