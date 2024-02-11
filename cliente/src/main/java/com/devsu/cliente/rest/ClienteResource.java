package com.devsu.cliente.rest;

import com.devsu.cliente.domain.Cliente;
import com.devsu.cliente.rest.util.ResponseUtil;
import com.devsu.cliente.service.crud.ClienteService;
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
import org.springframework.web.client.HttpClientErrorException;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing {@link Cliente}.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private final Logger log = LoggerFactory.getLogger(ClienteResource.class);

    private static final String ENTITY_NAME = "Cliente";

    @Value("${application.name}")
    private String applicationName;

    @Autowired
    private ClienteService clienteService;


    @PostMapping("")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) throws URISyntaxException {
        log.debug("REST request to save Cliente : {}", cliente);
        if (cliente.getId() != null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"A new cliente cannot already have an ID" + ENTITY_NAME + "idexists");
        }
        Cliente result = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(
        @PathVariable(value = "id", required = false) Long id,
        @RequestBody Cliente cliente
    ) throws BadRequestException {

        log.debug("REST request to update Cliente : {}, {}", id, cliente);
        if (cliente.getId() == null) {
            throw new BadRequestException("Invalid id" + ENTITY_NAME + "idnull");
        }
        if (!Objects.equals(id, cliente.getId())) {
            throw new BadRequestException("Invalid ID" + ENTITY_NAME +  "idinvalid");
        }

        if (!clienteService.existsById(id)) {
            throw new BadRequestException("Entity not found"  + ENTITY_NAME + "idnotfound");
        }

        Cliente result = clienteService.update(cliente);
        return ResponseEntity.ok(result);
    }


    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Cliente> partialUpdateCliente(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Cliente cliente
    ) throws BadRequestException {
        log.debug("REST request to partial update Cliente partially : {}, {}", id, cliente);
        if (cliente.getId() == null) {
            throw new BadRequestException("Invalid id" + ENTITY_NAME + "idnull");
        }
        if (!Objects.equals(id, cliente.getId())) {
            throw new BadRequestException("Invalid ID" + ENTITY_NAME + "idinvalid");
        }

        if (!clienteService.existsById(id)) {
            throw new BadRequestException("Entity not found" + ENTITY_NAME + "idnotfound");
        }

        return ResponseUtil.wrapOrNotFound( clienteService.partialUpdate(cliente));
    }

    @GetMapping("")
    public ResponseEntity<List<Cliente>> getAllClientes(Pageable pageable) {
        log.debug("REST request to get a page of Clientes");
        Page<Cliente> page = clienteService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("id") Long id) {
        log.debug("REST request to get Cliente : {}", id);
        return ResponseUtil.wrapOrNotFound(clienteService.findOne(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteCliente(@PathVariable("id") Long id) {
        log.debug("REST request to delete Cliente : {}", id);
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
