package com.devsu.movimiento.repository.report;

import com.devsu.movimiento.dto.EstadoCuentaReportResponseDTO;
import com.devsu.movimiento.repository.MovimientoRepository;
import org.springframework.data.repository.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Component
public interface ReportRepository extends MovimientoRepository {
    @Query(value = "SELECT m.fecha AS fecha, p.nombre AS nombreCliente, c.numero_cuenta AS numeroCuenta," +
            " c.tipo_cuenta AS tipo, c.saldo_inicial AS saldoInicial, c.estado AS estado, m.valor AS movimiento, m.saldo AS saldoDisponible " +
            "FROM movimiento m " +
            "INNER JOIN cuenta c ON m.cuenta_id = c.id " +
            "INNER JOIN cliente cli ON cli.id = c.cliente_id " +
            "INNER JOIN persona p ON p.id = cli.persona_id " +
            "WHERE m.fecha BETWEEN :desde AND :hasta " +
            "AND c.cliente_id = :clienteId", nativeQuery = true)
    List<EstadoCuentaReportResponseDTO> findEstadoCuentaReport(
            @Param("desde") Instant desde,
            @Param("hasta") Instant hasta,
            @Param("clienteId") Long clienteId);
}
