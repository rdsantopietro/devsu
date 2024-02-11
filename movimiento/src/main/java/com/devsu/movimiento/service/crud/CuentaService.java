package com.devsu.movimiento.service.crud;

import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.dto.CreateCuentaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.devsu.movimiento.domain.Cuenta}.
 */
public interface CuentaService {
    /**
     * Save a cuenta.
     *
     * @param cuenta the entity to save.
     * @return the persisted entity.
     */
    Cuenta save(Cuenta cuenta);

    /**
     * Updates a cuenta.
     *
     * @param cuenta the entity to update.
     * @return the persisted entity.
     */
    Cuenta update(Cuenta cuenta);

    /**
     * Partially updates a cuenta.
     *
     * @param cuenta the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Cuenta> partialUpdate(Cuenta cuenta);

    /**
     * Get all the cuentas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Cuenta> findAll(Pageable pageable);

    /**
     * Get the "id" cuenta.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Cuenta> findOne(Long id);

    /**
     * Delete the "id" cuenta.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
