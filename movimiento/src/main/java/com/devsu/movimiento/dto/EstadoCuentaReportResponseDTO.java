package com.devsu.movimiento.dto;

import com.devsu.movimiento.enums.TipoCuenta;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

public interface EstadoCuentaReportResponseDTO {

     Timestamp getFecha();

     String getNombreCliente();

     Long getNumeroCuenta();

     TipoCuenta getTipo();

     BigDecimal getSaldoInicial();

     Byte getEstado();

     BigDecimal getMovimiento();

     BigDecimal getSaldoDisponible();


}
