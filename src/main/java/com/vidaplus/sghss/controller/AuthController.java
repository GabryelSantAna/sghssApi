package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.AuthRequest;
import com.vidaplus.sghss.dto.AuthResponse;
import com.vidaplus.sghss.dto.UserRegisterRequest;
import com.vidaplus.sghss.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para autenticação e registro de usuários")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Registrar um novo usuário")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar um usuário e gerar token JWT")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
