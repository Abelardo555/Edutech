package com.example.Edutech.repository;


import com.example.Edutech.model.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContenidoRepository extends JpaRepository<Contenido, Long> {
    // Métodos personalizados opcionales
}