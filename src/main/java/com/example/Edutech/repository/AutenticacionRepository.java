package com.example.Edutech.repository;


import com.example.Edutech.model.Autenticacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutenticacionRepository extends JpaRepository<Autenticacion, Long> {
    Optional<Autenticacion> findByCorreo(String correo);
}