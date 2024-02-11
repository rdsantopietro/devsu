package com.devsu.movimiento.rest;

import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.dto.CreateMovimientoDTO;
import com.devsu.movimiento.service.ApplyMovimientoService;
import com.devsu.movimiento.service.crud.MovimientoService;
import com.devsu.movimiento.service.error.NonExistingCuenta;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/movimientos")
public class MovimientoResource {

    private final Logger log = LoggerFactory.getLogger(MovimientoResource.class);

    private static final String ENTITY_NAME = "Movimiento";

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private ApplyMovimientoService applyMovimientoService;

    @PostMapping("")
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody CreateMovimientoDTO createMovimientoDTO) throws BadRequestException, NonExistingCuenta {
        log.debug("REST request to save Movimiento : {}", createMovimientoDTO);
        if (Objects.isNull(createMovimientoDTO.getValor()))
            throw new BadRequestException("The amount of the movement cannot be null");
        Movimiento result = applyMovimientoService.applyMovimiento(createMovimientoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(
            @PathVariable(value = "id", required = false) Long id,
            @RequestBody Movimiento movimiento
    ) throws BadRequestException {
        log.debug("REST request to update Movimiento : {}, {}", id, movimiento);
        if (movimiento.getId() == null) {
            throw new BadRequestException("Invalid id: ID cannot be null");
        }
        if (!Objects.equals(id, movimiento.getId())) {
            throw new BadRequestException("Invalid ID: Path ID does not match Movimiento ID");
        }
        if (!movimientoService.findOne(id).isPresent()) {
            throw new BadRequestException("Entity not found: Movimiento with ID " + id + " not found");
        }
        Movimiento result = movimientoService.update(movimiento);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Movimiento> partialUpdateMovimiento(
            @PathVariable(value = "id", required = false) Long id,
            @RequestBody Movimiento movimiento
    ) throws BadRequestException {
        log.debug("REST request to partial update Movimiento partially : {}, {}", id, movimiento);
        if (movimiento.getId() == null) {
            throw new BadRequestException("Invalid id: ID cannot be null");
        }
        if (!Objects.equals(id, movimiento.getId())) {
            throw new BadRequestException("Invalid ID: Path ID does not match Movimiento ID");
        }
        if (!movimientoService.findOne(id).isPresent()) {
            throw new BadRequestException("Entity not found: Movimiento with ID " + id + " not found");
        }
        // Implementa la lógica de actualización parcial aquí
        return ResponseEntity.ok(null); // Puedes personalizar el mensaje de éxito aquí
    }

    @GetMapping("")
    public ResponseEntity<List<Movimiento>> getAllMovimientos(Pageable pageable) {
        log.debug("REST request to get a page of Movimientos");
        Page<Movimiento> page = movimientoService.findAll(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimiento(@PathVariable("id") Long id) {
        log.debug("REST request to get Movimiento : {}", id);
        return ResponseEntity.of(movimientoService.findOne(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable("id") Long id) {
        log.debug("REST request to delete Movimiento : {}", id);
        movimientoService.delete(id);
        return ResponseEntity.ok().build(); // Puedes personalizar el mensaje de éxito aquí
    }
}
