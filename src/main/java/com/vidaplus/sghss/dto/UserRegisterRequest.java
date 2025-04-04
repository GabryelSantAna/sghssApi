package com.vidaplus.sghss.dto;

import com.vidaplus.sghss.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Requisição para registro de usuário")
public class UserRegisterRequest {

    @Schema(description = "Nome do usuário", example = "João da Silva")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "joao@email.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "senha123")
    private String password;

    @Schema(description = "Papel do usuário", example = "PACIENTE")
    private UserRole role;

    // Novos campos para paciente
    @Schema(description = "CPF do paciente", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "Data de nascimento do paciente", example = "1985-07-20")
    private LocalDate dataNascimento;

    @Schema(description = "Endereço do paciente", example = "Rua das Flores, 123")
    private String endereco;

    @Schema(description = "Telefone do paciente", example = "(11) 98765-4321")
    private String telefone;
}
