package com.devsu.movimiento.service;

import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.dto.CreateMovimientoDTO;
import com.devsu.movimiento.enums.TipoMovimiento;
import com.devsu.movimiento.repository.MovimientoRepository;

import com.devsu.movimiento.service.crud.MovimientoService;
import com.devsu.movimiento.service.error.NonExistingCuenta;
import com.devsu.movimiento.service.error.SaldoInsuficienteException;
import com.devsu.movimiento.service.impl.ApplyMovimientoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplyMovimientoServiceTest {

    @Mock
    private SaldoValidator saldoValidator;

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaSearchByNumeroService cuentaSearchByNumeroService;

    @Mock
    private MovimientoService movimientoService;

    @Mock
    private GetLastSaldoService getLastSaldoService;

    @InjectMocks
    private ApplyMovimientoServiceImpl applyMovimientoService;

    private CreateMovimientoDTO createMovimientoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);createMovimientoDTO = new CreateMovimientoDTO();
        createMovimientoDTO.setNumeroCuenta(12345L);
        createMovimientoDTO.setTipoMovimiento(TipoMovimiento.AHORROS);
        createMovimientoDTO.setValor(BigDecimal.valueOf(100));

    }

    @Test
    void applyMovimiento_ValidMovimiento_ShouldSaveAndReturnMovimiento() throws NonExistingCuenta, SaldoInsuficienteException {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta(12345L);
        cuenta.setSaldoInicial(BigDecimal.valueOf(1000));
        cuenta.setEstado(true);

        when(getLastSaldoService.getLastSaldo(createMovimientoDTO.getNumeroCuenta())).thenReturn(BigDecimal.valueOf(1100));
        when(saldoValidator.isValidSaldo(createMovimientoDTO)).thenReturn(true);
        when(cuentaSearchByNumeroService.searchByNumeroCuenta(createMovimientoDTO.getNumeroCuenta())).thenReturn(Optional.of(cuenta));

        Movimiento movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setFecha(Instant.now());
        movimiento.setTipoMovimiento(createMovimientoDTO.getTipoMovimiento());
        movimiento.setSaldo(createMovimientoDTO.getSaldo());
        movimiento.setCuenta(cuenta);
        movimiento.setValor(createMovimientoDTO.getValor());

        when(movimientoService.save(any(Movimiento.class))).thenReturn(movimiento);

        Movimiento result = applyMovimientoService.applyMovimiento(createMovimientoDTO);

        assertNotNull(result);
        assertEquals(movimiento, result);
    }

    @Test
    void applyMovimiento_SaldoInsuficiente_ShouldThrowSaldoInsuficienteException() throws NonExistingCuenta, SaldoInsuficienteException {

        when(getLastSaldoService.getLastSaldo(createMovimientoDTO.getNumeroCuenta())).thenReturn(BigDecimal.valueOf(50));
        when(saldoValidator.isValidSaldo(createMovimientoDTO)).thenReturn(false);

        assertThrows(SaldoInsuficienteException.class, () -> {
            applyMovimientoService.applyMovimiento(createMovimientoDTO);
        });

        verify(cuentaSearchByNumeroService, never()).searchByNumeroCuenta(anyLong());
        verify(movimientoService, never()).save(any());
    }
}