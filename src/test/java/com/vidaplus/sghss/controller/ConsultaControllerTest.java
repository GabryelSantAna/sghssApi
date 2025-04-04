package com.vidaplus.sghss.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidaplus.sghss.config.TestSecurityConfig;
import com.vidaplus.sghss.dto.ConsultaDTO;
import com.vidaplus.sghss.enums.StatusConsulta;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test // CT03
    void deveAgendarConsultaComDadosValidos() throws Exception {
        ConsultaDTO request = new ConsultaDTO();
        request.setPacienteId(1L);
        request.setMedicoId(2L);
        request.setDataHora(LocalDateTime.now().plusDays(2));
        request.setStatus(StatusConsulta.AGENDADA);

        mockMvc.perform(post("/api/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.status").value("AGENDADA"));
    }
}
