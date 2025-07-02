package com.example.Edutech.service;

import com.example.Edutech.model.Inscripcion;

import java.util.List;
import java.util.Optional;

public interface InscripcionService {
    Inscripcion crearInscripcion(Inscripcion inscripcion);
    Optional<Inscripcion> obtenerInscripcionPorId(Long id);
    List<Inscripcion> listarInscripciones();
    Inscripcion actualizarInscripcion(Long id, Inscripcion inscripcion);
    void eliminarInscripcion(Long id);
}