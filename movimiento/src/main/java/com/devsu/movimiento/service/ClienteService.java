package com.devsu.movimiento.service;

import com.devsu.movimiento.domain.Cliente;
import com.devsu.movimiento.service.error.MoreThanOneResultException;
import com.devsu.movimiento.service.error.NonExistingCliente;

public interface ClienteService {


    Cliente searchClienteById(Long clienteId) throws MoreThanOneResultException, NonExistingCliente;;

}
