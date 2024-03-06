package com.devsu.movimiento.rest;

import com.devsu.movimiento.dto.EstadoCuentaReportInputDTO;
import com.devsu.movimiento.dto.EstadoCuentaReportResponseDTO;
import com.devsu.movimiento.service.report.CuentaEstadoReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for managing reportes}.
 */
@RestController
@RequestMapping("/movimientos/reportes")
public class ReportesResource {

    private final Logger log = LoggerFactory.getLogger(ReportesResource.class);

    @Value("${application.name}")
    private String applicationName;

    @Autowired
    private CuentaEstadoReportService cuentaEstadoReportService;


    @GetMapping("")
    public ResponseEntity<List<EstadoCuentaReportResponseDTO>> getMovimientoReports(
            @RequestParam("desde")LocalDate desde,
            @RequestParam("hasta")LocalDate hasta,
            @RequestParam("clienteId")Long clienteId) {
        EstadoCuentaReportInputDTO estadoCuentaReportInputDTO = EstadoCuentaReportInputDTO.builder()
                        .desde(desde)
                        .to(hasta)
                        .clienteId(clienteId).build();
        log.debug("REST request to get Report : {}", estadoCuentaReportInputDTO);

        List<EstadoCuentaReportResponseDTO> result = cuentaEstadoReportService.getCuentaEstadoReport(estadoCuentaReportInputDTO);
        return ResponseEntity.ok().body(result);
    }

}
