package com.example.Edutech.controller;

import com.example.Edutech.assemblers.InscripcionModelAssembler;
import com.example.Edutech.model.Inscripcion;
import com.example.Edutech.service.InscripcionService;
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