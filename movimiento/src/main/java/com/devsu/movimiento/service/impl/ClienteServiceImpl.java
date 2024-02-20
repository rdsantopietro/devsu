package com.devsu.movimiento.service.impl;

import com.devsu.movimiento.domain.Cliente;
import com.devsu.movimiento.rest.CuentaResource;
import com.devsu.movimiento.service.ClienteService;
import com.devsu.movimiento.service.error.MoreThanOneResultException;
import com.devsu.movimiento.service.error.NonExistingCliente;
import com.devsu.movimiento.utils.ClienteRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final Logger log = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    ClienteRest clienteRest;

    @Override
    public Cliente searchClienteById(Long clienteId) throws NonExistingCliente {
        log.debug("Buscando cliente : ", clienteId);
        return clienteRest.getClienteByClienteId(clienteId).orElseThrow(NonExistingCliente::new);
    }
}
