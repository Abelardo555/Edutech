package com.example.Edutech.service;

import com.example.Edutech.model.Autenticacion;

import java.util.Optional;

public interface AutenticacionService {
    Optional<Autenticacion> login(String correo, String contrasena);
}