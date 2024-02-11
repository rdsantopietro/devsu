package com.devsu.movimiento.service.impl;

import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.repository.MovimientoRepository;
import com.devsu.movimiento.service.CuentaSearchByNumeroService;
import com.devsu.movimiento.service.crud.CuentaService;
import com.devsu.movimiento.service.GetLastSaldoService;
import com.devsu.movimiento.service.error.NonExistingCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class GetLastSaldoServiceImpl implements GetLastSaldoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaSearchByNumeroService cuentaSearchByNumeroService;
    @Override
    public BigDecimal getLastSaldo(Long numeroCuneta) throws NonExistingCuenta {
        return movimientoRepository.findFirstByCuentaNumeroCuentaOrderByFechaDesc(numeroCuneta)
                .map(Movimiento::getSaldo)
                .orElse(cuentaSearchByNumeroService.searchByNumeroCuenta(numeroCuneta).orElseThrow(NonExistingCuenta::new).getSaldoInicial());
    }
}
