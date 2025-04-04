package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.model.Internacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternacaoRepository extends JpaRepository<Internacao, Long> {
    List<Internacao> findByPacienteId(Long pacienteId);
}
