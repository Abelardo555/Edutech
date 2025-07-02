package com.example.Edutech.service.impl;

import com.example.Edutech.model.Autenticacion;
import com.example.Edutech.repository.AutenticacionRepository;
import com.example.Edutech.service.AutenticacionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    private final AutenticacionRepository autenticacionRepository;

    public AutenticacionServiceImpl(AutenticacionRepository autenticacionRepository) {
        this.autenticacionRepository = autenticacionRepository;
    }

    @Override
    public Optional<Autenticacion> login(String correo, String contrasena) {
        return autenticacionRepository.findByCorreo(correo)
            .filter(user -> user.getContrasena().equals(contrasena));
    }
}