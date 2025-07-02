package com.example.Edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contenidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcontenido;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 10000)
    private String descripcion;

    @Column(nullable = false)
    private String urlArchivo; // Puede ser ruta o URL del archivo de contenido

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcurso", nullable = false)
    private Curso curso;
}