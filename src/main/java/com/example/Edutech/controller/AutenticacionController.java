package com.example.Edutech.controller;

import com.example.Edutech.model.Autenticacion;
import com.example.Edutech.service.AutenticacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Controlador para manejar el login de usuarios")
public class AutenticacionController {

    @Autowired
    private AutenticacionService autenticacionService;

    @Operation(
        summary = "Iniciar sesión",
        description = "Permite a un usuario iniciar sesión con su correo y contraseña. Devuelve un mensaje de éxito o credenciales inválidas."
    )
    @PostMapping("/login")
    public String login(@RequestBody Autenticacion datos) {
        return autenticacionService.login(datos.getCorreo(), datos.getContrasena())
                .map(auth -> "Login exitoso")
                .orElse("Credenciales inválidas");
    }
}
