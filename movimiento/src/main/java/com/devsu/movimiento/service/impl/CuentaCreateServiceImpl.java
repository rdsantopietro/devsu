package com.devsu.movimiento.service.impl;

import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.dto.CreateCuentaDTO;
import com.devsu.movimiento.service.ClienteService;
import com.devsu.movimiento.service.CuentaCreateService;
import com.devsu.movimiento.service.crud.CuentaService;
import com.devsu.movimiento.service.error.MoreThanOneResultException;
import com.devsu.movimiento.service.error.NonExistingCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaCreateServiceImpl implements CuentaCreateService {

    @Autowired
    CuentaService cuentaService;

    @Autowired
    ClienteService clienteService;

    public Cuenta create(CreateCuentaDTO createCuentaDTO) throws MoreThanOneResultException, NonExistingCliente{
        Cuenta cuenta = Cuenta.builder()
                .numeroCuenta(createCuentaDTO.getNumeroCuenta())
                .tipoCuenta(createCuentaDTO.getTipoCuenta())
                .saldoInicial(createCuentaDTO.getSaldoInicial())
                .cliente(clienteService.searchClienteById(createCuentaDTO.getClienteId()))
                .estado(createCuentaDTO.getEstado())
                .build();
        return cuentaService.save(cuenta);
    };
}
