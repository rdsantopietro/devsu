package com.devsu.movimiento.rest;

import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.dto.CreateCuentaDTO;
import com.devsu.movimiento.repository.CuentaRepository;
import com.devsu.movimiento.rest.util.ResponseUtil;
import com.devsu.movimiento.service.CuentaCreateService;
import com.devsu.movimiento.service.crud.CuentaService;
import com.devsu.movimiento.service.error.MoreThanOneResultException;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing {@link Cuenta}.
 */
@RestController
@RequestMapping("/cuentas")
public class CuentaResource {

    private final Logger log = LoggerFactory.getLogger(CuentaResource.class);

    private static final String ENTITY_NAME = "Cuenta";

    @Value("${application.name}")
    private String applicationName;
    
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private CuentaCreateService cuentaCreateService;


    @Autowired
    private CuentaRepository cuentaRepository;

    @PostMapping("")
    public ResponseEntity<Cuenta> createCuenta(@RequestBody CreateCuentaDTO cuenta) throws MoreThanOneResultException {
        log.debug("REST request to save Cuenta : {}", cuenta);
        Cuenta result = cuentaCreateService.create(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(
            @PathVariable(value = "id", required = false) Long id,
            @RequestBody Cuenta cuenta
    ) throws BadRequestException {

        log.debug("REST request to update Cuenta : {}, {}", id, cuenta);
        if (cuenta.getId() == null) {
            throw new BadRequestException("Invalid id" + ENTITY_NAME + "idnull");
        }
        if (!Objects.equals(id, cuenta.getId())) {
            throw new BadRequestException("Invalid ID" + ENTITY_NAME +  "idinvalid");
        }

        if (!cuentaRepository.existsById(id)) {
            throw new BadRequestException("Entity not found"  + ENTITY_NAME + "idnotfound");
        }

        Cuenta result = cuentaService.update(cuenta);
        return ResponseEntity.ok(result);
    }


    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Cuenta> partialUpdateCuenta(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody Cuenta cuenta
    ) throws BadRequestException {
        log.debug("REST request to partial update Cuenta partially : {}, {}", id, cuenta);
        if (cuenta.getId() == null) {
            throw new BadRequestException("Invalid id" + ENTITY_NAME + "idnull");
        }
        if (!Objects.equals(id, cuenta.getId())) {
            throw new BadRequestException("Invalid ID" + ENTITY_NAME + "idinvalid");
        }

        if (!cuentaRepository.existsById(id)) {
            throw new BadRequestException("Entity not found" + ENTITY_NAME + "idnotfound");
        }

        return ResponseUtil.wrapOrNotFound( cuentaService.partialUpdate(cuenta));
    }

    @GetMapping("")
    public ResponseEntity<List<Cuenta>> getAllCuentas(Pageable pageable) {
        log.debug("REST request to get a page of Cuentas");
        Page<Cuenta> page = cuentaService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuenta(@PathVariable("id") Long id) {
        log.debug("REST request to get Cuenta : {}", id);
        return ResponseUtil.wrapOrNotFound(cuentaService.findOne(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCuenta(@PathVariable("id") Long id) {
        log.debug("REST request to delete Cuenta : {}", id);
        cuentaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
