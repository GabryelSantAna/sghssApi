package com.vidaplus.sghss.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import com.vidaplus.sghss.enums.StatusLeito;
import com.vidaplus.sghss.enums.TipoLeito;

@Data
@Entity
@Table(name = "leitos")
@Schema(description = "Gerenciamento de leitos hospitalares")
public class Leito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do leito", example = "1")
    private Long id;

    @Schema(description = "Número do leito", example = "101")
    private int numero;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo do leito", example = "UTI")
    private TipoLeito tipo;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Status do leito", example = "DISPONIVEL")
    private StatusLeito status;
}
