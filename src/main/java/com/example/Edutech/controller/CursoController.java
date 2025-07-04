package com.example.Edutech.controller;

import com.example.Edutech.assemblers.CursoModelAssembler;
import com.example.Edutech.model.Curso;
import com.example.Edutech.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

    @Operation(
        summary = "Obtener curso por ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Curso encontrado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Curso.class),
                    examples = @ExampleObject(
                        value = """
                        {
                          "idcurso": 1,
                          "nombre": "Java Básico",
                          "descripcion": "Curso de introducción a Java",
                          "categoria": "Programación",
                          "duracionHoras": 20,
                          "_links": {
                            "self": { "href": "http://localhost:8080/cursos/1" },
                            "cursos": { "href": "http://localhost:8080/cursos" }
                          }
                        }
                        """
                    )
                )
            )
        }
    )
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
