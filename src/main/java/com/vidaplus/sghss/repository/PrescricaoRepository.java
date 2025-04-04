package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.model.Prescricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {
    List<Prescricao> findByPacienteId(Long pacienteId);
}
