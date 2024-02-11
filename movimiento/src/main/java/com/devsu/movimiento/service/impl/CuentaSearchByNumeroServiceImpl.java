package com.devsu.movimiento.service.impl;

import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.repository.CuentaRepository;
import com.devsu.movimiento.service.CuentaSearchByNumeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaSearchByNumeroServiceImpl implements CuentaSearchByNumeroService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Optional<Cuenta> searchByNumeroCuenta(Long numeroCuenta ) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }
}
