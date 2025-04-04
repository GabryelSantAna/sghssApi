package com.vidaplus.sghss.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "exames")
@Schema(description = "Registro de exames enviados pelos pacientes")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do exame", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @Schema(description = "Paciente que enviou o exame")
    private Paciente paciente;

    @Schema(description = "Nome do arquivo do exame", example = "exame-sangue.pdf")
    private String nomeArquivo;

    @Schema(description = "URL do arquivo armazenado", example = "https://s3.amazonaws.com/meu-bucket/exame-sangue.pdf")
    private String urlArquivo;

    @Schema(description = "Data e hora do envio do exame")
    private LocalDateTime dataEnvio;
}
