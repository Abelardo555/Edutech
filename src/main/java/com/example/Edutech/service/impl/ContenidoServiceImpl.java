package com.example.Edutech.service.impl;

import com.example.Edutech.model.Contenido;
import com.example.Edutech.repository.ContenidoRepository;
import com.example.Edutech.service.ContenidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContenidoServiceImpl implements ContenidoService {

    private final ContenidoRepository contenidoRepository;

    public ContenidoServiceImpl(ContenidoRepository contenidoRepository) {
        this.contenidoRepository = contenidoRepository;
    }

    @Override
    public Contenido crearContenido(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    @Override
    public Optional<Contenido> obtenerContenidoPorId(Long id) {
        return contenidoRepository.findById(id);
    }

    @Override
    public List<Contenido> listarContenidos() {
        return contenidoRepository.findAll();
    }

    @Override
    public Contenido actualizarContenido(Long id, Contenido contenido) {
        return contenidoRepository.findById(id)
            .map(c -> {
                c.setTitulo(contenido.getTitulo());
                c.setDescripcion(contenido.getDescripcion());
                c.setUrl(contenido.getUrl()); // <--- Cambia esto
                c.setCurso(contenido.getCurso());
                return contenidoRepository.save(c);
            })
            .orElseThrow(() -> new RuntimeException("Contenido no encontrado con id " + id));
    }

    @Override
    public void eliminarContenido(Long id) {
        contenidoRepository.deleteById(id);
    }
}
