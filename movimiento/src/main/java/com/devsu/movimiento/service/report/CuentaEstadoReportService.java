package com.devsu.movimiento.service.report;

import com.devsu.movimiento.dto.EstadoCuentaReportInputDTO;
import com.devsu.movimiento.dto.EstadoCuentaReportResponseDTO;
import com.devsu.movimiento.service.error.NonExistingCuenta;

import java.util.List;

public interface CuentaEstadoReportService {

    List<EstadoCuentaReportResponseDTO> getCuentaEstadoReport(EstadoCuentaReportInputDTO estadoCuentaReportInputDTO) throws NonExistingCuenta;
}
