package com.example.Edutech.controller;

import com.example.Edutech.model.Curso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Controlador para manejar los cursos del sistema")
public class CursoController {

    @Operation(
        summary = "Obtener curso por ID",
        description = "Devuelve un curso específico según su ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long id) {
        return ResponseEntity.ok(new Curso());
    }

    @Operation(
        summary = "Listar todos los cursos",
        description = "Muestra una lista con todos los cursos registrados en el sistema."
    )
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(
        summary = "Crear un nuevo curso",
        description = "Crea un nuevo curso usando los datos enviados en el cuerpo de la petición."
    )
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(curso);
    }

    @Operation(
        summary = "Actualizar curso",
        description = "Actualiza la información de un curso según su ID."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        return ResponseEntity.ok(curso);
    }

    @Operation(
        summary = "Eliminar curso",
        description = "Elimina un curso del sistema usando su ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
