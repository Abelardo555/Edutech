package com.example.Edutech.controller;

import com.example.Edutech.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Controlador para manejar operaciones relacionadas con usuarios")
public class UsuarioController {

    @Operation(
        summary = "Obtener usuario por ID",
        description = "Devuelve un usuario específico según su ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(new Usuario());
    }

    @Operation(
        summary = "Listar todos los usuarios",
        description = "Muestra una lista de todos los usuarios registrados."
    )
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(
        summary = "Crear un nuevo usuario",
        description = "Crea un usuario usando los datos enviados en el cuerpo (JSON)."
    )
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuario);
    }

    @Operation(
        summary = "Actualizar un usuario",
        description = "Actualiza los datos de un usuario usando su ID."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuario);
    }

    @Operation(
        summary = "Eliminar un usuario",
        description = "Elimina un usuario del sistema usando su ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
