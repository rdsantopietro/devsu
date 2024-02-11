package com.devsu.movimiento.service.report.impl;

import com.devsu.movimiento.dto.EstadoCuentaReportInputDTO;
import com.devsu.movimiento.dto.EstadoCuentaReportResponseDTO;
import com.devsu.movimiento.repository.report.ReportRepository;
import com.devsu.movimiento.service.ClienteService;
import com.devsu.movimiento.service.CuentaSearchByClienteIdService;
import com.devsu.movimiento.service.crud.CuentaService;
import com.devsu.movimiento.service.crud.MovimientoService;
import com.devsu.movimiento.service.error.NonExistingCuenta;
import com.devsu.movimiento.service.report.CuentaEstadoReportService;
import com.devsu.movimiento.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaEstadoReportServiceImpl implements CuentaEstadoReportService {

    @Autowired
    CuentaService cuentaService;

    @Autowired
    CuentaSearchByClienteIdService cuentaSearchByClienteIdService;
    @Autowired
    MovimientoService movimientoService;
    @Autowired
    ClienteService clienteService;


    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<EstadoCuentaReportResponseDTO> getCuentaEstadoReport(EstadoCuentaReportInputDTO inputDTO) throws NonExistingCuenta {
        return reportRepository.findEstadoCuentaReport(DateUtils.localDateToInstant(inputDTO.getDesde()),
                DateUtils.localDateToInstant(inputDTO.getTo()), inputDTO.getClienteId());
    }

}
