package com.vidaplus.sghss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
@ConfigurationPropertiesScan("com.vidaplus.sghss.config")  // Habilita a leitura das propriedades
public class SghssApplication {

	public static void main(String[] args) {
		SpringApplication.run(SghssApplication.class, args);
	}

}
