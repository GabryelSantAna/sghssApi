package com.vidaplus.sghss.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "prescricoes")
@Schema(description = "Registro de prescrições médicas")
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da prescrição", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @Schema(description = "Paciente que recebeu a prescrição")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    @Schema(description = "Médico que emitiu a prescrição")
    private User medico;

    @Schema(description = "Descrição dos medicamentos e recomendações", example = "Paracetamol 500mg - Tomar 1 comprimido a cada 8 horas por 5 dias.")
    private String descricao;

    @Schema(description = "Data e hora da prescrição")
    private LocalDateTime dataEmissao;
}
