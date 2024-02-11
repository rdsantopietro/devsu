package com.devsu.cliente.service.crud.impl;

import com.devsu.cliente.domain.Persona;
import com.devsu.cliente.repository.PersonaRepository;
import com.devsu.cliente.service.crud.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Persona}.
 */
@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {


    private final Logger log = LoggerFactory.getLogger(PersonaServiceImpl.class);

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona save(Persona persona) {
        log.debug("Request to save Persona : {}", persona);
        return personaRepository.save(persona);
    }

    @Override
    public Persona update(Persona persona) {
        log.debug("Request to update Persona : {}", persona);
        return personaRepository.save(persona);
    }

    @Override
    public Optional<Persona> partialUpdate(Persona persona) {
        log.debug("Request to partially update Persona : {}", persona);

        return personaRepository.findById(persona.getId())
            .map(existingPersona -> {
                if(persona.getEdad() != null){
                    existingPersona.setEdad(persona.getEdad());
                }
                if(persona.getDireccion() != null){
                    existingPersona.setDireccion(persona.getDireccion());
                }
                if(persona.getGenero() != null){
                    existingPersona.setGenero(persona.getGenero());
                }
                if(persona.getNombre() != null){
                    existingPersona.setNombre(persona.getNombre());
                }

                if(persona.getIdentificacion() != null){
                    existingPersona.setIdentificacion(persona.getIdentificacion());
                }

                if(persona.getTelefono() != null){
                    existingPersona.setTelefono(persona.getTelefono());
                }
                return existingPersona;
            }).map(personaRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> findAll(Pageable pageable) {
        log.debug("Request to get all Personas");
        return personaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findOne(Long id) {
        log.debug("Request to get Persona : {}", id);
        return personaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Persona : {}", id);
        personaRepository.deleteById(id);
    }
}
