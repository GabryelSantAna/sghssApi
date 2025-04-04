package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.Prescricao;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.model.User;
import com.vidaplus.sghss.repository.PrescricaoRepository;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescricaoService {

    private final PrescricaoRepository prescricaoRepository;
    private final PacienteRepository pacienteRepository;
    private final UserRepository userRepository;

    @Transactional
    public Prescricao emitirPrescricao(Long pacienteId, Long medicoId, String descricao) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        User medico = userRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Prescricao prescricao = new Prescricao();
        prescricao.setPaciente(paciente);
        prescricao.setMedico(medico);
        prescricao.setDescricao(descricao);
        prescricao.setDataEmissao(LocalDateTime.now());

        return prescricaoRepository.save(prescricao);
    }

    public List<Prescricao> listarPrescricoesPorPaciente(Long pacienteId) {
        return prescricaoRepository.findByPacienteId(pacienteId);
    }
}
