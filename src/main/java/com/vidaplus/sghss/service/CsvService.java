package com.vidaplus.sghss.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vidaplus.sghss.model.LogAuditoria;
import com.vidaplus.sghss.repository.LogAuditoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CsvService {

    private final LogAuditoriaRepository logAuditoriaRepository;

    public byte[] gerarCsvLogs() {
        List<LogAuditoria> logs = logAuditoriaRepository.findByOrderByDataHoraDesc();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);

        writer.println("Data/Hora,Usuário,Ação");
        for (LogAuditoria log : logs) {
            writer.println(log.getDataHora() + "," + log.getUsuario() + "," + log.getAcao());
        }

        writer.flush();
        return outputStream.toByteArray();
    }
}
