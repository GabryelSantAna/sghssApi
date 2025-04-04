package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.LogAuditoria;
import com.vidaplus.sghss.repository.LogAuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogAuditoriaService {

    private final LogAuditoriaRepository logAuditoriaRepository;

    @Transactional
    public void registrarLog(String acao) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();

        LogAuditoria log = new LogAuditoria();
        log.setUsuario(usuario);
        log.setAcao(acao);
        log.setDataHora(LocalDateTime.now());

        logAuditoriaRepository.save(log);
    }

    public List<LogAuditoria> listarLogs() {
        return logAuditoriaRepository.findByOrderByDataHoraDesc();
    }
}
