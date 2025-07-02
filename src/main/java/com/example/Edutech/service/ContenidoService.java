package com.example.Edutech.service;

import com.example.Edutech.model.Contenido;

import java.util.List;
import java.util.Optional;

public interface ContenidoService {
    Contenido crearContenido(Contenido contenido);
    Optional<Contenido> obtenerContenidoPorId(Long id);
    List<Contenido> listarContenidos();
    Contenido actualizarContenido(Long id, Contenido contenido);
    void eliminarContenido(Long id);
}