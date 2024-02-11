package com.devsu.movimiento.service.impl;

import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.dto.CreateMovimientoDTO;
import com.devsu.movimiento.repository.MovimientoRepository;
import com.devsu.movimiento.service.*;
import com.devsu.movimiento.service.crud.MovimientoService;
import com.devsu.movimiento.service.error.NonExistingCuenta;
import com.devsu.movimiento.service.error.SaldoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ApplyMovimientoServiceImpl implements ApplyMovimientoService {

    @Autowired
    private SaldoValidator saldoValidator;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaSearchByNumeroService cuentaSearchByNumeroService;

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private GetLastSaldoService getLastSaldoService;

    @Override
    public Movimiento applyMovimiento(CreateMovimientoDTO createMovimientoDTO) throws NonExistingCuenta, SaldoInsuficienteException {
        createMovimientoDTO.setSaldo(getLastSaldoService.getLastSaldo(createMovimientoDTO.getNumeroCuenta()).add(createMovimientoDTO.getValor()));
        if(!saldoValidator.isValidSaldo(createMovimientoDTO))
            throw new SaldoInsuficienteException("La cuenta " + createMovimientoDTO.getNumeroCuenta() + " no tiene saldo suficiente para realizar la operacón");
        Movimiento movimiento =  Movimiento.builder()
                .tipoMovimiento(createMovimientoDTO.getTipoMovimiento())
                .fecha(Instant.now())
                .saldo(createMovimientoDTO.getSaldo())
                .cuenta(cuentaSearchByNumeroService.searchByNumeroCuenta(createMovimientoDTO.getNumeroCuenta()).orElseThrow(NonExistingCuenta::new))
                .valor(createMovimientoDTO.getValor())
                .build();
        return movimientoService.save(movimiento);
    }

}
