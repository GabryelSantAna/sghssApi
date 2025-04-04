package com.vidaplus.sghss.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "internacoes")
@Schema(description = "Registro de internações hospitalares")
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da internação", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @Schema(description = "Paciente internado")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "leito_id", nullable = false)
    @Schema(description = "Leito onde o paciente está internado")
    private Leito leito;

    @Schema(description = "Data de início da internação")
    private LocalDateTime dataEntrada;

    @Schema(description = "Data de alta do paciente")
    private LocalDateTime dataSaida;
}
