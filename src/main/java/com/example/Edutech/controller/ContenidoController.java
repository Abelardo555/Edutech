package com.example.Edutech.controller;

import com.example.Edutech.model.Contenido;
import com.example.Edutech.service.ContenidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contenidos")
public class ContenidoController {

    private final ContenidoService contenidoService;

    public ContenidoController(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    @PostMapping
    public ResponseEntity<Contenido> crearContenido(@RequestBody Contenido contenido) {
        return ResponseEntity.ok(contenidoService.crearContenido(contenido));
    }

    @Operation(
        summary = "Obtener contenido por ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Contenido encontrado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Contenido.class),
                    examples = @ExampleObject(
                        value = """
                        {
                          "idcontenido": 1,
                          "titulo": "Introducción a Java",
                          "descripcion": "Primer módulo del curso Java Básico",
                          "url": "https://ejemplo.com/java-intro",
                          "curso": {
                            "idcurso": 1,
                            "nombre": "Java Básico"
                          }
                        }
                        """
                    )
                )
            )
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Contenido> obtenerContenido(@PathVariable Long id) {
        return contenidoService.obtenerContenidoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Contenido>> listarContenidos() {
        return ResponseEntity.ok(contenidoService.listarContenidos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contenido> actualizarContenido(@PathVariable Long id, @RequestBody Contenido contenido) {
        return ResponseEntity.ok(contenidoService.actualizarContenido(id, contenido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContenido(@PathVariable Long id) {
        contenidoService.eliminarContenido(id);
        return ResponseEntity.noContent().build();
    }
}