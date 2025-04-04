package com.vidaplus.sghss.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tipos de leitos disponíveis no hospital")
public enum TipoLeito {
    UTI,  
    ENFERMARIA,  
    APARTAMENTO  
}
