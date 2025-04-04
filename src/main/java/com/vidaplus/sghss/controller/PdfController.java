package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/relatorios")
@RequiredArgsConstructor
public class PdfController {

    private final PdfService pdfService;

    @GetMapping("/administrativo")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<byte[]> gerarRelatorioAdministrativo() {
        byte[] pdf = pdfService.gerarRelatorioAdministrativo();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_administrativo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}
