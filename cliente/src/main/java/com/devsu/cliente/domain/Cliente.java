package com.devsu.cliente.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * A Cliente.
 */
@Entity
@Data
@PrimaryKeyJoinColumn(name = "persona_id")
public class Cliente extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contrasena;

    private Boolean estado;

}
