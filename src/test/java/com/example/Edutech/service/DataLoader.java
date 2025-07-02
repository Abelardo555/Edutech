package com.example.Edutech.service;

import com.example.Edutech.model.Usuario;
import com.example.Edutech.model.Curso;
import com.example.Edutech.repository.UsuarioRepository;
import com.example.Edutech.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public DataLoader(UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public void run(String... args) {
        // Agrega usuarios de prueba
        usuarioRepository.save(new Usuario(null, "Juan", "Pérez", "juan@correo.com", "1234", "ESTUDIANTE"));
        usuarioRepository.save(new Usuario(null, "Ana", "García", "ana@correo.com", "abcd", "PROFESOR"));

        // Agrega cursos de prueba
        cursoRepository.save(new Curso(null, "Java Básico", "Curso de introducción a Java", "Programación", 20));
        cursoRepository.save(new Curso(null, "Spring Boot", "Curso de Spring Boot", "Programación", 30));
    }
}
