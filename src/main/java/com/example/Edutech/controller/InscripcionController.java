package com.example.Edutech.controller;

import com.example.Edutech.assemblers.InscripcionModelAssembler;
import com.example.Edutech.model.Inscripcion;
import com.example.Edutech.service.InscripcionService;
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
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;
    private final InscripcionModelAssembler inscripcionModelAssembler;

    public InscripcionController(InscripcionService inscripcionService, InscripcionModelAssembler inscripcionModelAssembler) {
        this.inscripcionService = inscripcionService;
        this.inscripcionModelAssembler = inscripcionModelAssembler;
    }

    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcionService.crearInscripcion(inscripcion));
    }

    @Operation(
        summary = "Obtener inscripción por ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Inscripción encontrada",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Inscripcion.class),
                    examples = @ExampleObject(
                        value = """
                        {
                          "idinscripcion": 1,
                          "fecha": "2024-07-04",
                          "usuario": {
                            "idusuario": 1,
                            "nombre": "Juan",
                            "apellido": "Pérez"
                          },
                          "curso": {
                            "idcurso": 1,
                            "nombre": "Java Básico"
                          },
                          "_links": {
                            "self": { "href": "http://localhost:8080/inscripciones/1" },
                            "inscripciones": { "href": "http://localhost:8080/inscripciones" }
                          }
                        }
                        """
                    )
                )
            )
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Inscripcion>> obtenerInscripcion(@PathVariable Long id) {
        return inscripcionService.obtenerInscripcionPorId(id)
                .map(inscripcionModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Inscripcion>>> listarInscripciones() {
        List<EntityModel<Inscripcion>> inscripciones = inscripcionService.listarInscripciones().stream()
                .map(inscripcionModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(inscripciones,
                        linkTo(methodOn(InscripcionController.class).listarInscripciones()).withSelfRel())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizarInscripcion(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcionService.actualizarInscripcion(id, inscripcion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}