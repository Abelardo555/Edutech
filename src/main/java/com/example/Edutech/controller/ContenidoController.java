package com.example.Edutech.controller;

import com.example.Edutech.model.Contenido;
import com.example.Edutech.service.ContenidoService;
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