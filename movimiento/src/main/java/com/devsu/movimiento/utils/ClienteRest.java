package com.devsu.movimiento.utils;


import com.devsu.movimiento.domain.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Service
@FeignClient(name = "${cliente.service.name}")
public interface ClienteRest {

	@GetMapping("/clientes/{id}")
	Optional<Cliente> getClienteByClienteId(@PathVariable("id") Long id);
}
