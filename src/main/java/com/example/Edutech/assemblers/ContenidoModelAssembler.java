package com.example.Edutech.assemblers;

import com.example.Edutech.controller.ContenidoController;
import com.example.Edutech.model.Contenido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ContenidoModelAssembler implements RepresentationModelAssembler<Contenido, EntityModel<Contenido>> {

    @Override
    public @NonNull EntityModel<Contenido> toModel(@NonNull Contenido contenido) {
        return EntityModel.of(contenido,
                linkTo(methodOn(ContenidoController.class).obtenerContenido(contenido.getIdcontenido())).withSelfRel(),
                linkTo(methodOn(ContenidoController.class).listarContenidos()).withRel("contenidos")
        );
    }
}