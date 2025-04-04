package com.vidaplus.sghss.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Papéis disponíveis para os usuários do sistema")
public enum UserRole {
    SUPER_ADMIN,  
    ADMIN,  
    MEDICO,  
    PACIENTE  
}
