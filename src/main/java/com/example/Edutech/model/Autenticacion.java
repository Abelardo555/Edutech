package com.example.Edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autenticacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autenticacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String correo;

    private String contrasena;
}