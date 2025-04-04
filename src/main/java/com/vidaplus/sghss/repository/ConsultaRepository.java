package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.enums.StatusConsulta;
import com.vidaplus.sghss.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMedicoIdAndDataHoraBetween(Long medicoId, LocalDateTime inicio, LocalDateTime fim);
    List<Consulta> findByPacienteId(Long pacienteId);
    List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    long countByStatus(StatusConsulta status);}
