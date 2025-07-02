package com.example.Edutech.service.impl;

import com.example.Edutech.model.Inscripcion;
import com.example.Edutech.repository.InscripcionRepository;
import com.example.Edutech.service.InscripcionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    public InscripcionServiceImpl(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public Inscripcion crearInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public Optional<Inscripcion> obtenerInscripcionPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    @Override
    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion actualizarInscripcion(Long id, Inscripcion inscripcion) {
        return inscripcionRepository.findById(id)
            .map(i -> {
                i.setUsuario(inscripcion.getUsuario());
                i.setCurso(inscripcion.getCurso());
                i.setFechaInscripcion(inscripcion.getFechaInscripcion());
                i.setEstado(inscripcion.getEstado());
                return inscripcionRepository.save(i);
            })
            .orElseThrow(() -> new RuntimeException("Inscripción no encontrada con id " + id));
    }

    @Override
    public void eliminarInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }
}