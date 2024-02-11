package com.devsu.cliente.service.crud.impl;

import com.devsu.cliente.domain.Cliente;
import com.devsu.cliente.repository.ClienteRepository;
import com.devsu.cliente.service.crud.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cliente}.
 */
@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) {
        log.debug("Request to save Cliente : {}", cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        log.debug("Request to update Cliente : {}", cliente);
        return  clienteRepository.save(cliente);

    }

    @Override
    public Optional<Cliente> partialUpdate(Cliente cliente) {
        log.debug("Request to partially update Cliente : {}", cliente);

        return clienteRepository.findById(cliente.getId())
            .map(existingCliente -> {
                if(cliente.getEstado() != null){
                    existingCliente.setEstado(cliente.getEstado());
                }
                if(cliente.getContrasena() != null){
                    existingCliente.setContrasena(cliente.getContrasena());
                }
                if(cliente.getEdad() != null){
                    existingCliente.setEdad(cliente.getEdad());
                }
                if(cliente.getDireccion() != null){
                    existingCliente.setDireccion(cliente.getDireccion());
                }
                if(cliente.getGenero() != null){
                    existingCliente.setGenero(cliente.getGenero());
                }
                if(cliente.getNombre() != null){
                    existingCliente.setNombre(cliente.getNombre());
                }

                if(cliente.getIdentificacion() != null){
                    existingCliente.setIdentificacion(cliente.getIdentificacion());
                }

                if(cliente.getTelefono() != null){
                    existingCliente.setTelefono(cliente.getTelefono());
                }
                return existingCliente;
            }).map(clienteRepository::save);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        log.debug("Request to get all Clientes");
        return clienteRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findOne(Long id) {
        log.debug("Request to get Cliente : {}", id);
        return clienteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cliente : {}", id);
        clienteRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }
}
