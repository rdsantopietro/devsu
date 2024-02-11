package com.devsu.movimiento.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * A Cliente.
 */
@Entity
@Data
public class Cliente extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contrasena;

    private Boolean estado;



}
