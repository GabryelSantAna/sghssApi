package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.model.LogAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Long> {
    List<LogAuditoria> findByOrderByDataHoraDesc();
}
