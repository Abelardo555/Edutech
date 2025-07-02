package com.example.Edutech.assemblers;

import com.example.Edutech.controller.InscripcionController;
import com.example.Edutech.model.Inscripcion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InscripcionModelAssembler implements RepresentationModelAssembler<Inscripcion, EntityModel<Inscripcion>> {

    @Override
    public EntityModel<Inscripcion> toModel(Inscripcion inscripcion) {
        return EntityModel.of(inscripcion,
                linkTo(methodOn(InscripcionController.class).obtenerInscripcion(inscripcion.getIdinscripcion())).withSelfRel(),
                linkTo(methodOn(InscripcionController.class).listarInscripciones()).withRel("inscripciones")
        );
    }
}
