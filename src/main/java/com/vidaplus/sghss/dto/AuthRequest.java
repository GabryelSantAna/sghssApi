package com.vidaplus.sghss.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Requisição de autenticação do usuário")
public class AuthRequest {

    @Schema(description = "E-mail do usuário", example = "usuario@email.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "senha123")
    private String password;
}
