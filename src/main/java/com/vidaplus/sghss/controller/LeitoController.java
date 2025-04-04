package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.model.Leito;
import com.vidaplus.sghss.enums.StatusLeito;
import com.vidaplus.sghss.repository.LeitoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leitos")
@RequiredArgsConstructor
public class LeitoController {

    private final LeitoRepository leitoRepository;

    @GetMapping("/disponiveis")
    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN')")
    public ResponseEntity<List<Leito>> listarLeitosDisponiveis() {
        return ResponseEntity.ok(leitoRepository.findByStatus(StatusLeito.DISPONIVEL));
    }
}
