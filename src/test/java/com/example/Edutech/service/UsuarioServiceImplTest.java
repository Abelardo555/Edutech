package com.example.Edutech.service;

import com.example.Edutech.model.Usuario;
import com.example.Edutech.repository.UsuarioRepository;
import com.example.Edutech.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Test
    void testCrearUsuario() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioServiceImpl service = new UsuarioServiceImpl(repo);

        Usuario usuario = new Usuario(null, "Test", "User", "test@correo.com", "pass", "ESTUDIANTE");
        when(repo.save(usuario)).thenReturn(usuario);

        Usuario creado = service.crearUsuario(usuario);
        assertEquals("Test", creado.getNombre());
        verify(repo, times(1)).save(usuario);
    }

    @Test
    void testObtenerUsuarioPorId() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioServiceImpl service = new UsuarioServiceImpl(repo);

        Usuario usuario = new Usuario(1L, "Test", "User", "test@correo.com", "pass", "ESTUDIANTE");
        when(repo.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> encontrado = service.obtenerUsuarioPorId(1L);
        assertTrue(encontrado.isPresent());
        assertEquals("Test", encontrado.get().getNombre());
    }

    @Test
    void testListarUsuarios() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioServiceImpl service = new UsuarioServiceImpl(repo);

        Usuario usuario1 = new Usuario(1L, "Test1", "User1", "test1@correo.com", "pass1", "ESTUDIANTE");
        Usuario usuario2 = new Usuario(2L, "Test2", "User2", "test2@correo.com", "pass2", "PROFESOR");
        when(repo.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<Usuario> usuarios = service.listarUsuarios();
        assertEquals(2, usuarios.size());
    }

    @Test
    void testActualizarUsuario() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioServiceImpl service = new UsuarioServiceImpl(repo);

        Usuario original = new Usuario(1L, "Test", "User", "test@correo.com", "pass", "ESTUDIANTE");
        Usuario actualizado = new Usuario(1L, "Nuevo", "Apellido", "nuevo@correo.com", "nuevaPass", "PROFESOR");

        when(repo.findById(1L)).thenReturn(Optional.of(original));
        when(repo.save(any(Usuario.class))).thenReturn(actualizado);

        Usuario result = service.actualizarUsuario(1L, actualizado);
        assertEquals("Nuevo", result.getNombre());
        assertEquals("PROFESOR", result.getRol());
    }

    @Test
    void testEliminarUsuario() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioServiceImpl service = new UsuarioServiceImpl(repo);

        service.eliminarUsuario(1L);
        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    void testObtenerUsuarioPorCorreo() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioServiceImpl service = new UsuarioServiceImpl(repo);

        Usuario usuario = new Usuario(1L, "Test", "User", "test@correo.com", "pass", "ESTUDIANTE");
        when(repo.findByCorreo("test@correo.com")).thenReturn(Optional.of(usuario));

        Optional<Usuario> encontrado = service.obtenerUsuarioPorCorreo("test@correo.com");
        assertTrue(encontrado.isPresent());
        assertEquals("Test", encontrado.get().getNombre());
    }
}