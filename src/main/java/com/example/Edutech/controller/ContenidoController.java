package com.example.Edutech.controller;

import com.example.Edutech.model.Contenido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contenidos")
@Tag(name = "Contenidos", description = "Controlador para gestionar los contenidos del sistema")
public class ContenidoController {

    @Operation(
        summary = "Obtener contenido por ID",
        description = "Devuelve un contenido específico según su ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Contenido> obtenerContenido(@PathVariable Long id) {
        return ResponseEntity.ok(new Contenido());
    }

    @Operation(
        summary = "Listar todos los contenidos",
        description = "Muestra una lista de todos los contenidos registrados."
    )
    @GetMapping
    public ResponseEntity<List<Contenido>> listarContenidos() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(
        summary = "Crear nuevo contenido",
        description = "Crea un contenido nuevo usando los datos enviados en el cuerpo (JSON)."
    )
    @PostMapping
    public ResponseEntity<Contenido> crearContenido(@RequestBody Contenido contenido) {
        return ResponseEntity.ok(contenido);
    }

    @Operation(
        summary = "Actualizar contenido",
        description = "Actualiza los datos de un contenido existente según su ID."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Contenido> actualizarContenido(@PathVariable Long id, @RequestBody Contenido contenido) {
        return ResponseEntity.ok(contenido);
    }

    @Operation(
        summary = "Eliminar contenido",
        description = "Elimina un contenido del sistema usando su ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContenido(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
