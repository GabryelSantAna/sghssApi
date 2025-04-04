package com.vidaplus.sghss.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vidaplus.sghss.model.Internacao;
import com.vidaplus.sghss.service.InternacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/internacoes")
@RequiredArgsConstructor
public class InternacaoController {

    private final InternacaoService internacaoService;

    @PostMapping("/internar")
    @PreAuthorize("hasRole('MEDICO')")
    public ResponseEntity<Internacao> internarPaciente(@RequestParam Long pacienteId, @RequestParam Long leitoId) {
        return ResponseEntity.ok(internacaoService.internarPaciente(pacienteId, leitoId));
    }

    @PutMapping("/alta/{internacaoId}")
    @PreAuthorize("hasRole('MEDICO')")
    public ResponseEntity<Void> darAlta(@PathVariable Long internacaoId) {
        internacaoService.darAlta(internacaoId);
        return ResponseEntity.noContent().build();
    }
}
