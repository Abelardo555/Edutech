package com.example.Edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contenido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcontenido;

    private String titulo;
    private String descripcion;
    private String url;

    @ManyToOne
    @JoinColumn(name = "idcurso", nullable = false)
    private Curso curso;
}