package com.example.Edutech.controller;

import com.example.Edutech.assemblers.UsuarioModelAssembler;
import com.example.Edutech.model.Usuario;
import com.example.Edutech.service.UsuarioService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler usuarioModelAssembler;

    public UsuarioController(UsuarioService usuarioService, UsuarioModelAssembler usuarioModelAssembler) {
        this.usuarioService = usuarioService;
        this.usuarioModelAssembler = usuarioModelAssembler;
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id)
                .map(usuarioModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> listarUsuarios() {
        List<EntityModel<Usuario>> usuarios = usuarioService.listarUsuarios().stream()
                .map(usuarioModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(usuarios,
                        linkTo(methodOn(UsuarioController.class).listarUsuarios()).withSelfRel())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
