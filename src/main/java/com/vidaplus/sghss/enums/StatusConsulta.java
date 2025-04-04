package com.vidaplus.sghss.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Status das consultas médicas")
public enum StatusConsulta {
    AGENDADA,  
    CANCELADA,  
    REALIZADA  
}
