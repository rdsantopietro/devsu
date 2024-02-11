package com.devsu.movimiento.repository;

import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.dto.EstadoCuentaReportResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Movimiento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    Optional<Movimiento> findFirstByCuentaNumeroCuentaOrderByFechaDesc(Long numeroCuenta);
    List<Movimiento> findByCuentaIdAndFechaBetweenOrderByFecha(Long cuentaId, Instant fechaInicio, Instant fechaFin);

}
