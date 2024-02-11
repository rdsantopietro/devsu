package com.devsu.movimiento.service.crud.impl;


import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.repository.CuentaRepository;
import com.devsu.movimiento.service.crud.CuentaService;
import com.devsu.movimiento.utils.ClienteRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CuentaServiceImpl  implements CuentaService {

    private final Logger log = LoggerFactory.getLogger(CuentaServiceImpl.class);

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRest clienteRest;

    @Override
    public Cuenta save(Cuenta cuenta) {
        log.debug("Request to save Cuenta : {}", cuenta);
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta update(Cuenta cuenta) {
        log.debug("Request to update Cuenta : {}", cuenta);
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Optional<Cuenta> partialUpdate(Cuenta cuenta) {
        log.debug("Request to partially update Cuenta : {}", cuenta);

        return cuentaRepository
                .findById(cuenta.getId())
                .map(existingCuenta -> {
                    if (cuenta.getNumeroCuenta() != null) {
                        existingCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
                    }
                    if (cuenta.getTipoCuenta() != null) {
                        existingCuenta.setTipoCuenta(cuenta.getTipoCuenta());
                    }
                    if (cuenta.getSaldoInicial() != null) {
                        existingCuenta.setSaldoInicial(cuenta.getSaldoInicial());
                    }
                    if (cuenta.getEstado() != null) {
                        existingCuenta.setEstado(cuenta.getEstado());
                    }

                    return existingCuenta;
                })
                .map(cuentaRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cuenta> findAll(Pageable pageable) {
        log.debug("Request to get all Cuentas");
            return cuentaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cuenta> findOne(Long id) {
        log.debug("Request to get Cuenta : {}", id);
        return cuentaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cuenta : {}", id);
        cuentaRepository.deleteById(id);
    }


}
