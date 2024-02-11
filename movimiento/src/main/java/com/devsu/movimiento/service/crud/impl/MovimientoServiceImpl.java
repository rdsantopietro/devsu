package com.devsu.movimiento.service.crud.impl;

import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.repository.MovimientoRepository;
import com.devsu.movimiento.service.crud.CuentaService;
import com.devsu.movimiento.service.crud.MovimientoService;
import com.devsu.movimiento.service.SaldoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final Logger log = LoggerFactory.getLogger(MovimientoServiceImpl.class);

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private SaldoValidator saldoValidator;

    @Autowired
    private CuentaService cuentaService;

    @Override
    public Movimiento save(Movimiento movimiento) {
        log.debug("Request to save Movimiento : {}", movimiento);
        return movimientoRepository.save(movimiento);
    }
    @Override
    public Movimiento update(Movimiento movimiento) {
        log.debug("Request to update Movimiento : {}", movimiento);
        return movimientoRepository.save(movimiento);
    }

    @Override
    public Optional<Movimiento> partialUpdate(Movimiento movimiento) {
        log.debug("Request to partially update Movimiento : {}", movimiento);

        return movimientoRepository
                .findById(movimiento.getId())
                .map(existingMovimiento -> {
                    if (movimiento.getFecha() != null) {
                        existingMovimiento.setFecha(movimiento.getFecha());
                    }
                    if (movimiento.getTipoMovimiento() != null) {
                        existingMovimiento.setTipoMovimiento(movimiento.getTipoMovimiento());
                    }
                    if (movimiento.getValor() != null) {
                        existingMovimiento.setValor(movimiento.getValor());
                    }
                    if (movimiento.getSaldo() != null) {
                        existingMovimiento.setSaldo(movimiento.getSaldo());
                    }
                    if (movimiento.getCuenta() != null) {
                        existingMovimiento.setCuenta(movimiento.getCuenta());
                    }

                    return existingMovimiento;
                })
                .map(movimientoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Movimiento> findAll(Pageable pageable) {
        log.debug("Request to get all Movimientos");
        return movimientoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movimiento> findOne(Long id) {
        log.debug("Request to get Movimiento : {}", id);
        return movimientoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Movimiento : {}", id);
        movimientoRepository.deleteById(id);
    }
}