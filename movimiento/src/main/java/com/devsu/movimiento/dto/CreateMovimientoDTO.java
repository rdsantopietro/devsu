package com.devsu.movimiento.dto;

import com.devsu.movimiento.enums.TipoCuenta;
import com.devsu.movimiento.enums.TipoMovimiento;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CreateMovimientoDTO {

    private Long numeroCuenta;

    private TipoMovimiento tipoMovimiento;

    private BigDecimal saldo;

    private Boolean estado;

    private BigDecimal valor;

}
