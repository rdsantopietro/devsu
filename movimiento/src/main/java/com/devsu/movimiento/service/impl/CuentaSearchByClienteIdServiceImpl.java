package com.devsu.movimiento.service.impl;

import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.repository.CuentaRepository;
import com.devsu.movimiento.service.CuentaSearchByClienteIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaSearchByClienteIdServiceImpl implements CuentaSearchByClienteIdService {

    @Autowired
    CuentaRepository cuentaRepository;
    @Override
    public Optional<Cuenta> searchByClienteId(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }
}
