package com.devsu.movimiento.service.impl;

import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.dto.CreateMovimientoDTO;
import com.devsu.movimiento.service.SaldoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaldoValidatorImpl implements SaldoValidator {
    @Override
    public Boolean isValidSaldo(CreateMovimientoDTO movimiento) {
        return movimiento.getSaldo().compareTo(BigDecimal.ZERO)>=0;
    }
}
