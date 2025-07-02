package com.example.Edutech.service.impl;

import com.example.Edutech.model.Usuario;
import com.example.Edutech.repository.UsuarioRepository;
import com.example.Edutech.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        return usuarioRepository.findById(id)
            .map(u -> {
                u.setNombre(usuario.getNombre());
                u.setApellido(usuario.getApellido());
                u.setCorreo(usuario.getCorreo());
                u.setContrasena(usuario.getContrasena());
                u.setRol(usuario.getRol());
                return usuarioRepository.save(u);
            })
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}