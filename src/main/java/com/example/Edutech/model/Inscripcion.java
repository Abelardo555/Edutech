package com.example.Edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idinscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcurso", nullable = false)
    private Curso curso;

    @Column(nullable = false)
    private LocalDateTime fechaInscripcion;

    @Column(nullable = false)
    private String estado; // Ej: "ACTIVO", "CANCELADO", "COMPLETADO"
}