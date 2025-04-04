package com.vidaplus.sghss.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vidaplus.sghss.model.LogAuditoria;
import com.vidaplus.sghss.service.CsvService;
import com.vidaplus.sghss.service.LogAuditoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogAuditoriaController {

    private final LogAuditoriaService logService;
    private final CsvService csvService;
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<LogAuditoria>> listarLogs() {
        return ResponseEntity.ok(logService.listarLogs());
    }
    

    @GetMapping("/exportar/csv")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<byte[]> exportarLogsCsv() {
        byte[] csv = csvService.gerarCsvLogs();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=logs_auditoria.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);
    }

}
