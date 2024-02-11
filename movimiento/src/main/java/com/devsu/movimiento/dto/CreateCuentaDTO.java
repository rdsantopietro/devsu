package com.devsu.movimiento.dto;

import com.devsu.movimiento.enums.TipoCuenta;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCuentaDTO {

    private Long numeroCuenta;

    private TipoCuenta tipoCuenta;

    private BigDecimal saldoInicial;

    private Boolean estado;

    private Long clienteId;
}
