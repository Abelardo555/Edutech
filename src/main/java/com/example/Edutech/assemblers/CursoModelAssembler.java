package com.example.Edutech.assemblers;

import com.example.Edutech.controller.CursoController;
import com.example.Edutech.model.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
                linkTo(methodOn(CursoController.class).obtenerCurso(curso.getIdcurso())).withSelfRel(),
                linkTo(methodOn(CursoController.class).listarCursos()).withRel("cursos")
        );
    }
}
