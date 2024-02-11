package com.devsu.movimiento.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * A Persona.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer edad;

    private String identificacion;

    private String direccion;

    private String telefono;

}
