package com.devsu.movimiento.service.crud;

import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.service.error.SaldoInsuficienteException;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MovimientoService {
    /**
     * Guarda un movimiento.
     *
     * @param movimiento la entidad a guardar.
     * @return la entidad persistida.
     */
    Movimiento save(Movimiento movimiento);

    /**
     * Actualiza un movimiento.
     *
     * @param movimiento la entidad a actualizar.
     * @return la entidad persistida.
     */
    Movimiento update(Movimiento movimiento);

    /**
     * Actualiza parcialmente un movimiento.
     *
     * @param movimiento la entidad a actualizar parcialmente.
     * @return la entidad persistida.
     */
    Optional<Movimiento> partialUpdate(Movimiento movimiento);

    /**
     * Obtiene todos los movimientos.
     *
     * @param pageable información de paginación.
     * @return la lista de entidades.
     */
    Page<Movimiento> findAll(Pageable pageable);

    /**
     * Obtiene el movimiento con el "id" especificado.
     *
     * @param id el id de la entidad.
     * @return la entidad.
     */
    Optional<Movimiento> findOne(Long id);

    /**
     * Elimina el movimiento con el "id" especificado.
     *
     * @param id el id de la entidad.
     */
    void delete(Long id);
}
