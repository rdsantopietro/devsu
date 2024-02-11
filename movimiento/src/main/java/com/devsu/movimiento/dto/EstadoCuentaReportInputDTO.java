package com.devsu.movimiento.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EstadoCuentaReportInputDTO {

    LocalDate desde;
    LocalDate to;
    Long clienteId;


}
