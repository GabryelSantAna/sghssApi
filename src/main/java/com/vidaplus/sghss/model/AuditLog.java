package com.vidaplus.sghss.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "audit_log")
@Schema(description = "Registro de auditoria do sistema")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do log de auditoria", example = "1")
    private Long id;

    @Schema(description = "Usuário que realizou a ação", example = "admin@email.com")
    private String usuario;

    @Schema(description = "Ação realizada no sistema", example = "DELETE PACIENTE ID 10")
    private String acao;

    @Schema(description = "Data e hora da ação")
    private LocalDateTime timestamp;

    public AuditLog(String usuario, String acao) {
        this.usuario = usuario;
        this.acao = acao;
        this.timestamp = LocalDateTime.now();
    }

    public AuditLog() {
    }
}
