package com.vidaplus.sghss.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pacientes")
@Schema(description = "Representa um paciente no sistema")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do paciente", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nome completo do paciente", example = "João da Silva")
    private String nome;

    @Column(nullable = false, unique = true)
    @Schema(description = "CPF do paciente", example = "123.456.789-00")
    private String cpf;

    @Column(nullable = false)
    @Schema(description = "Data de nascimento do paciente", example = "1985-07-20")
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Schema(description = "Endereço do paciente", example = "Rua das Flores, 123")
    private String endereco;

    @Column(nullable = false)
    @Schema(description = "Telefone de contato", example = "(11) 98765-4321")
    private String telefone;

    @Column(nullable = false, unique = true)
    @Schema(description = "E-mail do paciente", example = "joao.silva@email.com")
    private String email;
}
