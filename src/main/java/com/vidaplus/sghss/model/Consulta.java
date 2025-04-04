package com.vidaplus.sghss.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import com.vidaplus.sghss.enums.StatusConsulta;

@Data
@Entity
@Table(name = "consultas")
@Schema(description = "Registro de consulta médica")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da consulta", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @Schema(description = "Paciente que agendou a consulta")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    @Schema(description = "Médico responsável pela consulta")
    private User medico;

    @Column(nullable = false)
    @Schema(description = "Data e hora da consulta", example = "2025-04-15T14:30:00")
    private LocalDateTime dataHora;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Status da consulta", example = "AGENDADA")
    private StatusConsulta status;

    @Schema(description = "Link para a videochamada da consulta", example = "https://meet.jit.si/consulta-12345")
    private String linkVideo;
}
