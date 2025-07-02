package com.example.Edutech.service;

import com.example.Edutech.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    Curso crearCurso(Curso curso);
    Optional<Curso> obtenerCursoPorId(Long id);
    List<Curso> listarCursos();
    Curso actualizarCurso(Long id, Curso curso);
    void eliminarCurso(Long id);
}
