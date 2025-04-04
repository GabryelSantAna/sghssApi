package com.vidaplus.sghss.service;

import com.vidaplus.sghss.dto.ConsultaDTO;
import com.vidaplus.sghss.model.Consulta;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.model.User;
import com.vidaplus.sghss.enums.StatusConsulta;
import com.vidaplus.sghss.repository.ConsultaRepository;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Tag(name = "Consulta Service", description = "Serviço responsável pelo agendamento de consultas")
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuditLogService auditLogService;

    @Transactional
    public ConsultaDTO agendarConsulta(ConsultaDTO consultaDTO) {
        Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        User medico = userRepository.findById(consultaDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Consulta consulta = modelMapper.map(consultaDTO, Consulta.class);
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setStatus(StatusConsulta.AGENDADA);

        // Gerar link único de videochamada no Jitsi Meet
        String linkVideo = "https://meet.jit.si/consulta-" + UUID.randomUUID();
        consulta.setLinkVideo(linkVideo);

        consultaRepository.save(consulta);
        auditLogService.registrarAcao("AGENDOU CONSULTA " + consulta.getId());

        return modelMapper.map(consulta, ConsultaDTO.class);
    }

    public List<ConsultaDTO> listarConsultasPorPaciente(Long pacienteId) {
        List<Consulta> consultas = consultaRepository.findByPacienteId(pacienteId);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelarConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setStatus(StatusConsulta.CANCELADA);
        consultaRepository.save(consulta);
        auditLogService.registrarAcao("CANCELOU CONSULTA " + id);
    }
    
    public ConsultaDTO buscarConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        return modelMapper.map(consulta, ConsultaDTO.class);
    }

}
