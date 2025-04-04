package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.model.Exame;
import com.vidaplus.sghss.service.ExameService;
import com.vidaplus.sghss.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/exames")
@RequiredArgsConstructor
@Tag(name = "Exames", description = "Endpoints para envio e listagem de exames")
public class ExameController {

    private final ExameService exameService;
    private final FileStorageService fileStorageService;

    @PostMapping("/upload/{pacienteId}")
    @PreAuthorize("hasRole('PACIENTE')")
    @Operation(summary = "Enviar um exame para o sistema")
    public ResponseEntity<String> enviarExame(@PathVariable Long pacienteId, @RequestParam("file") MultipartFile file) throws IOException {
        Exame exame = exameService.registrarExame(pacienteId, file);
        return ResponseEntity.ok("Exame enviado com sucesso: " + exame.getUrlArquivo());
    }

    @GetMapping("/{pacienteId}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN')")
    @Operation(summary = "Listar exames de um paciente")
    public ResponseEntity<List<Exame>> listarExames(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(exameService.listarExamesPorPaciente(pacienteId));
    }

    @GetMapping("/download/{fileName}")
    @PreAuthorize("hasAnyRole('MEDICO', 'PACIENTE', 'ADMIN')")
    @Operation(summary = "Baixar um exame armazenado")
    public ResponseEntity<byte[]> downloadArquivo(@PathVariable String fileName) {
        byte[] arquivo = fileStorageService.carregarArquivo("uploads/" + fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(arquivo);
    }
}
