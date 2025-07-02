package com.example.Edutech.service;

import com.example.Edutech.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    List<Usuario> listarUsuarios();
    Usuario actualizarUsuario(Long id, Usuario usuario);
    void eliminarUsuario(Long id);
    Optional<Usuario> obtenerUsuarioPorCorreo(String correo);
}