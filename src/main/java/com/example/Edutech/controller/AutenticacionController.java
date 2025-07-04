package com.example.Edutech.controller;

import com.example.Edutech.model.Autenticacion;
import com.example.Edutech.service.AutenticacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    @Operation(
        summary = "Login de usuario",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Login exitoso",
                content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(value = "Login exitoso")
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "Credenciales inválidas",
                content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(value = "Credenciales inválidas")
                )
            )
        }
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Autenticacion datos) {
        return autenticacionService.login(datos.getCorreo(), datos.getContrasena())
                .map(auth -> ResponseEntity.ok("Login exitoso"))
                .orElse(ResponseEntity.status(401).body("Credenciales inválidas"));
    }
}