package com.vidaplus.sghss.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SGHSS API")
                        .version("1.0")
                        .description("API do Sistema de Gestão Hospitalar e de Serviços de Saúde (SGHSS)"));
    }
}
