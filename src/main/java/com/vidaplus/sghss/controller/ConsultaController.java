package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.ConsultaDTO;
import com.vidaplus.sghss.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
@Tag(name = "Consulta", description = "Endpoints para gerenciamento de consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    @PreAuthorize("hasRole('PACIENTE')")
    @Operation(summary = "Agendar uma consulta")
    public ResponseEntity<ConsultaDTO> agendarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity.ok(consultaService.agendarConsulta(consultaDTO));
    }

    @GetMapping("/paciente/{pacienteId}")
    @PreAuthorize("hasAnyRole('PACIENTE', 'MEDICO', 'ADMIN')")
    @Operation(summary = "Listar consultas de um paciente")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.listarConsultasPorPaciente(pacienteId));
    }

    @PutMapping("/{id}/cancelar")
    @PreAuthorize("hasAnyRole('PACIENTE', 'MEDICO', 'ADMIN')")
    @Operation(summary = "Cancelar uma consulta")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PACIENTE', 'MEDICO', 'ADMIN')")
    @Operation(summary = "Obter detalhes de uma consulta, incluindo link de videochamada")
    public ResponseEntity<ConsultaDTO> buscarConsulta(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarConsulta(id));
    }
}
