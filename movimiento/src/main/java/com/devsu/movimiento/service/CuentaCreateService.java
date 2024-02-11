package com.devsu.movimiento.service;

import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.dto.CreateCuentaDTO;
import com.devsu.movimiento.service.error.MoreThanOneResultException;

public interface CuentaCreateService {
    Cuenta create(CreateCuentaDTO createCuentaDTO) throws MoreThanOneResultException;
}
