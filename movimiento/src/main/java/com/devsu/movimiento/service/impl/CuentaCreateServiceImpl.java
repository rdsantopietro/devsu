package com.devsu.movimiento.service.impl;

import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.dto.CreateCuentaDTO;
import com.devsu.movimiento.service.ClienteService;
import com.devsu.movimiento.service.CuentaCreateService;
import com.devsu.movimiento.service.crud.CuentaService;
import com.devsu.movimiento.service.error.MoreThanOneResultException;
import com.devsu.movimiento.service.error.NonExistingCliente;
import com.devsu.movimiento.service.messaging.MessagingSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CuentaCreateServiceImpl implements CuentaCreateService {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MessagingSendService messagingService;

    @Value("${messaging.queues.notification.new-account}")
    private String queque;

    public Cuenta create(CreateCuentaDTO createCuentaDTO) throws MoreThanOneResultException, NonExistingCliente{
        Cuenta cuenta = Cuenta.builder()
                .numeroCuenta(createCuentaDTO.getNumeroCuenta())
                .tipoCuenta(createCuentaDTO.getTipoCuenta())
                .saldoInicial(createCuentaDTO.getSaldoInicial())
                .cliente(clienteService.searchClienteById(createCuentaDTO.getClienteId()))
                .estado(createCuentaDTO.getEstado())
                .build();
        Cuenta result = cuentaService.save(cuenta);

        messagingService.sendMessage(queque, cuenta.toString());

        return result;
    };
}
