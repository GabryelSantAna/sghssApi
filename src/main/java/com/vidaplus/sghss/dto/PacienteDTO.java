package com.vidaplus.sghss.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "DTO para transferência de dados do Paciente")
public class PacienteDTO {

    @Schema(description = "ID do paciente", example = "1")
    private Long id;

    @Schema(description = "Nome do paciente", example = "João da Silva")
    private String nome;

    @Schema(description = "CPF do paciente", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "Data de nascimento do paciente", example = "1985-07-20")
    private LocalDate dataNascimento;

    @Schema(description = "Endereço do paciente", example = "Rua das Flores, 123")
    private String endereco;

    @Schema(description = "Telefone de contato", example = "(11) 98765-4321")
    private String telefone;

    @Schema(description = "E-mail do paciente", example = "joao.silva@email.com")
    private String email;
}
