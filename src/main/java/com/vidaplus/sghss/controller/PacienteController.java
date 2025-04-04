package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.PacienteDTO;
import com.vidaplus.sghss.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
@Tag(name = "Paciente Controller", description = "Controlador responsável pela gestão dos pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN')") // Apenas médicos e administradores podem acessar
    @Operation(summary = "Listar todos os pacientes")
    public ResponseEntity<List<PacienteDTO>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN', 'PACIENTE')") // Pacientes podem ver seus próprios dados
    @Operation(summary = "Buscar paciente por ID")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Apenas administradores podem cadastrar pacientes
    @Operation(summary = "Cadastrar um novo paciente")
    public ResponseEntity<PacienteDTO> salvar(@RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.salvar(pacienteDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICO')") // Apenas médicos e admins podem atualizar dados do paciente
    @Operation(summary = "Atualizar informações de um paciente")
    public ResponseEntity<PacienteDTO> atualizar(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.atualizar(id, pacienteDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Apenas administradores podem deletar pacientes
    @Operation(summary = "Deletar um paciente por ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
