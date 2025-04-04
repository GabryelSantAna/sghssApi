package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.model.Prescricao;
import com.vidaplus.sghss.service.PrescricaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescricoes")
@RequiredArgsConstructor
@Tag(name = "Prescrição Médica", description = "Endpoints para emissão e consulta de prescrições médicas")
public class PrescricaoController {

    private final PrescricaoService prescricaoService;

    @PostMapping("/emitir")
    @PreAuthorize("hasRole('MEDICO')")
    @Operation(summary = "Emitir uma prescrição médica")
    public ResponseEntity<Prescricao> emitirPrescricao(
            @RequestParam Long pacienteId,
            @RequestParam Long medicoId,
            @RequestParam String descricao) {
        
        return ResponseEntity.ok(prescricaoService.emitirPrescricao(pacienteId, medicoId, descricao));
    }

    @GetMapping("/{pacienteId}")
    @PreAuthorize("hasAnyRole('PACIENTE', 'MEDICO', 'ADMIN')")
    @Operation(summary = "Listar prescrições de um paciente")
    public ResponseEntity<List<Prescricao>> listarPrescricoes(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(prescricaoService.listarPrescricoesPorPaciente(pacienteId));
    }
}
