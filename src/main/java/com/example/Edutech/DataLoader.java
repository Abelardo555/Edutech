package com.example.Edutech;

import com.example.Edutech.model.Curso;
import com.example.Edutech.model.Usuario;
import com.example.Edutech.model.Inscripcion;
import com.example.Edutech.model.Contenido;
import com.example.Edutech.repository.CursoRepository;
import com.example.Edutech.repository.UsuarioRepository;
import com.example.Edutech.repository.InscripcionRepository;
import com.example.Edutech.repository.ContenidoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private ContenidoRepository contenidoRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();

        // Crear cursos
        for (int i = 0; i < 5; i++) {
            Curso curso = new Curso();
            curso.setNombre(faker.educator().course());
            curso.setDescripcion(faker.lorem().sentence());
            curso.setCategoria(faker.book().genre());
            curso.setDuracionHoras(faker.number().numberBetween(10, 60));
            cursoRepository.save(curso);
        }

        List<Curso> cursos = cursoRepository.findAll();

        // Crear usuarios
        for (int i = 0; i < 20; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombre(faker.name().firstName());
            usuario.setApellido(faker.name().lastName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setContrasena("1234");
            usuario.setRol(faker.options().option("ESTUDIANTE", "PROFESOR"));
            usuarioRepository.save(usuario);
        }

        List<Usuario> usuarios = usuarioRepository.findAll();

        // Crear inscripciones
        for (int i = 0; i < 15; i++) {
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setFechaInscripcion(LocalDateTime.now());
            inscripcion.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            inscripcion.setCurso(cursos.get(random.nextInt(cursos.size())));
            inscripcion.setEstado("ACTIVO");
            inscripcionRepository.save(inscripcion);
        }

        // Crear contenidos
        for (int i = 0; i < 10; i++) {
            Contenido contenido = new Contenido();
            contenido.setTitulo(faker.book().title());
            contenido.setDescripcion(faker.lorem().sentence());
            contenido.setUrl(faker.internet().url());
            contenido.setCurso(cursos.get(random.nextInt(cursos.size())));
            contenidoRepository.save(contenido);
        }
    }
}