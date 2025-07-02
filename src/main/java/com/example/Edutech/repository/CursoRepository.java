package com.example.Edutech.repository;


import com.example.Edutech.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    
}