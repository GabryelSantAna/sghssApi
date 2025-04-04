package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.AuditLog;
import com.vidaplus.sghss.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public void registrarAcao(String acao) {
        String usuario = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        AuditLog log = new AuditLog(usuario, acao);
        auditLogRepository.save(log);
    }

    public List<AuditLog> listarTodos() {
        return auditLogRepository.findAll();
    }
   
}
