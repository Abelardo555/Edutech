package com.example.Edutech.controller;

import com.example.Edutech.assemblers.CursoModelAssembler;
import com.example.Edutech.model.Curso;
import com.example.Edutech.service.CursoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;
    private final CursoModelAssembler cursoModelAssembler;

    public CursoController(CursoService cursoService, CursoModelAssembler cursoModelAssembler) {
        this.cursoService = cursoService;
        this.cursoModelAssembler = cursoModelAssembler;
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.crearCurso(curso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Curso>> obtenerCurso(@PathVariable Long id) {
        return cursoService.obtenerCursoPorId(id)
                .map(cursoModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Curso>>> listarCursos() {
        List<EntityModel<Curso>> cursos = cursoService.listarCursos().stream()
                .map(cursoModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(cursos,
                        linkTo(methodOn(CursoController.class).listarCursos()).withSelfRel())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.actualizarCurso(id, curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
