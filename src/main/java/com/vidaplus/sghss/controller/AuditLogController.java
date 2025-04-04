package com.vidaplus.sghss.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vidaplus.sghss.model.AuditLog;
import com.vidaplus.sghss.service.AuditLogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auditoria")
@RequiredArgsConstructor
@Tag(name = "Auditoria", description = "Endpoints para consulta de logs de auditoria")
public class AuditLogController {

	private final AuditLogService auditLogService;
	
	
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')") // Apenas admins podem ver os logs
	@Operation(summary = "Listar todos os registros de auditoria")
	public ResponseEntity<List<AuditLog>> listarTodos() {
		return ResponseEntity.ok(auditLogService.listarTodos());
	}


	

}
