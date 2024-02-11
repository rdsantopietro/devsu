package com.devsu.movimiento.domain;


import com.devsu.movimiento.enums.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A Cuenta.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant fecha;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @Column(precision = 21, scale = 2)
    private BigDecimal valor;

    @Column(precision = 21, scale = 2)
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    @JsonBackReference
    private Cuenta cuenta;

}
