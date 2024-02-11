package com.devsu.movimiento.service.impl;

import com.devsu.movimiento.domain.Cliente;
import com.devsu.movimiento.service.ClienteService;
import com.devsu.movimiento.service.error.MoreThanOneResultException;
import com.devsu.movimiento.service.error.NonExistingCliente;
import com.devsu.movimiento.utils.ClienteRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRest clienteRest;

    @Override
    public Cliente searchClienteById(Long clienteId) throws NonExistingCliente {
        return clienteRest.getClienteByClienteId(clienteId).orElseThrow(NonExistingCliente::new);
    }
}
