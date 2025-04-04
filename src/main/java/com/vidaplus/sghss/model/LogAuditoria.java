package com.vidaplus.sghss.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "logs_auditoria")
@Schema(description = "Registro de logs para auditoria do sistema")
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do log", example = "1")
    private Long id;

    @Schema(description = "Usuário que realizou a ação", example = "admin@hospital.com")
    private String usuario;

    @Schema(description = "Descrição da ação realizada", example = "Paciente João foi internado no leito 101")
    private String acao;

    @Schema(description = "Data e hora da ação")
    private LocalDateTime dataHora;
}
