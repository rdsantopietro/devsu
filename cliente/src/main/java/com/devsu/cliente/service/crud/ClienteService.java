package com.devsu.cliente.service.crud;

import com.devsu.cliente.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Cliente}.
 */
public interface ClienteService {

    Cliente save(Cliente cliente);

    Cliente update(Cliente cliente);

    Optional<Cliente> partialUpdate(Cliente cliente);

    Page<Cliente> findAll(Pageable pageable);

    Optional<Cliente> findOne(Long id);

    void delete(Long id);

    boolean existsById(Long id);
}
