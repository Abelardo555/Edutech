package com.example.Edutech.assemblers;

import com.example.Edutech.controller.UsuarioController;
import com.example.Edutech.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public @NonNull EntityModel<Usuario> toModel(@NonNull Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).obtenerUsuario(usuario.getIdusuario())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("usuarios")
        );
    }
}