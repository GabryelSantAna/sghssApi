package com.vidaplus.sghss.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vidaplus.sghss.service.FileStorageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/arquivos")
@RequiredArgsConstructor
@Tag(name = "Arquivo", description = "Endpoints para upload e download de arquivos")
public class FileController {

    private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('PACIENTE', 'MEDICO', 'ADMIN')")
    @Operation(summary = "Fazer upload de um arquivo")
    public ResponseEntity<String> uploadArquivo(@RequestParam("file") MultipartFile file) {
        String nomeArquivo = fileStorageService.salvarArquivo(file);
        return ResponseEntity.ok("Arquivo enviado com sucesso: " + nomeArquivo);
    }

    @GetMapping("/download/{nomeArquivo}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN')")
    @Operation(summary = "Baixar um arquivo armazenado")
    public ResponseEntity<byte[]> downloadArquivo(@PathVariable String nomeArquivo) {
        byte[] arquivo = fileStorageService.carregarArquivo(nomeArquivo);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nomeArquivo + "\"")
                .body(arquivo);
    }
}
