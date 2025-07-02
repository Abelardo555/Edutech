package com.example.Edutech.controller;

import com.example.Edutech.model.Autenticacion;
import com.example.Edutech.service.AutenticacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Autenticacion datos) {
        return autenticacionService.login(datos.getCorreo(), datos.getContrasena())
                .map(auth -> ResponseEntity.ok("Login exitoso"))
                .orElse(ResponseEntity.status(401).body("Credenciales inválidas"));
    }
}