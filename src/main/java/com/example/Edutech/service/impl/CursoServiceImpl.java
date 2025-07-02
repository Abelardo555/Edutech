package com.example.Edutech.service.impl;

import com.example.Edutech.model.Curso;
import com.example.Edutech.repository.CursoRepository;
import com.example.Edutech.service.CursoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso actualizarCurso(Long id, Curso curso) {
        return cursoRepository.findById(id)
            .map(c -> {
                c.setNombre(curso.getNombre());
                c.setDescripcion(curso.getDescripcion());
                c.setCategoria(curso.getCategoria());
                c.setDuracionHoras(curso.getDuracionHoras());
                return cursoRepository.save(c);
            })
            .orElseThrow(() -> new RuntimeException("Curso no encontrado con id " + id));
    }

    @Override
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}