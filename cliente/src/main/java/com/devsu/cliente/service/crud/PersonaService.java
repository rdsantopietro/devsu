package com.devsu.cliente.service.crud;

import com.devsu.cliente.domain.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Persona}.
 */
public interface PersonaService {

    Persona save(Persona persona);

    Persona update(Persona persona);
    Optional<Persona> partialUpdate(Persona persona);

    Page<Persona> findAll(Pageable pageable);

    Optional<Persona> findOne(Long id);

    void delete(Long id);
}
