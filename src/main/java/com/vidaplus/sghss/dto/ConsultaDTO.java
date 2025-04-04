package com.vidaplus.sghss.dto;

import com.vidaplus.sghss.enums.StatusConsulta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "DTO para transferência de dados da consulta")
public class ConsultaDTO {

    @Schema(description = "ID da consulta", example = "1")
    private Long id;

    @Schema(description = "ID do paciente", example = "10")
    private Long pacienteId;

    @Schema(description = "ID do médico", example = "5")
    private Long medicoId;

    @Schema(description = "Data e hora da consulta", example = "2025-04-15T14:30:00")
    private LocalDateTime dataHora;

    @Schema(description = "Status da consulta", example = "AGENDADA")
    private StatusConsulta status;
    
    @Schema(description = "Link para a videochamada da consulta", example = "https://meet.jit.si/consulta-123abc")
    private String linkVideo;
}
