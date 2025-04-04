package com.vidaplus.sghss.repository;


import com.vidaplus.sghss.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    Optional<Paciente> findByCpf(String cpf);
    
    Optional<Paciente> findByEmail(String email);
}
