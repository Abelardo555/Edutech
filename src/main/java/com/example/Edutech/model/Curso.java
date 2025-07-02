package com.example.Edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcurso;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    private String categoria;

    private int duracionHoras; // duración estimada del curso
}