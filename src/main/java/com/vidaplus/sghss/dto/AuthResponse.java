package com.vidaplus.sghss.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Resposta de autenticação contendo o token JWT")
public class AuthResponse {

    @Schema(description = "Token JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI...")
    private String token;
}
