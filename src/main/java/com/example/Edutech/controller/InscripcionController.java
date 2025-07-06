package com.example.Edutech.controller;

import com.example.Edutech.model.Inscripcion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
@Tag(name = "Inscripciones", description = "Controlador para gestionar las inscripciones de los usuarios a los cursos")
public class InscripcionController {

    @Operation(
        summary = "Obtener inscripción por ID",
        description = "Devuelve una inscripción específica según su ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerInscripcion(@PathVariable Long id) {
        return ResponseEntity.ok(new Inscripcion());
    }

    @Operation(
        summary = "Listar todas las inscripciones",
        description = "Muestra una lista de todas las inscripciones registradas en el sistema."
    )
    @GetMapping
    public ResponseEntity<List<Inscripcion>> listarInscripciones() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(
        summary = "Crear nueva inscripción",
        description = "Registra una nueva inscripción usando los datos enviados en el cuerpo de la petición."
    )
    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcion);
    }

    @Operation(
        summary = "Actualizar inscripción",
        description = "Actualiza la información de una inscripción existente según su ID."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizarInscripcion(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcion);
    }

    @Operation(
        summary = "Eliminar inscripción",
        description = "Elimina una inscripción del sistema usando su ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
