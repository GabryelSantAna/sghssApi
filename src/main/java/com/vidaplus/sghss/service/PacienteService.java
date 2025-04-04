package com.vidaplus.sghss.service;

import com.vidaplus.sghss.dto.PacienteDTO;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.repository.PacienteRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Tag(name = "Paciente Service", description = "Serviço responsável pela lógica de negócios dos pacientes")
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;
    private final AuditLogService auditLogService;
    private final EmailService emailService;

    public List<PacienteDTO> listarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        auditLogService.registrarAcao("LISTAR TODOS OS PACIENTES");
        return pacientes.stream()
                .map(paciente -> modelMapper.map(paciente, PacienteDTO.class))
                .collect(Collectors.toList());
    }

    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        auditLogService.registrarAcao("BUSCAR PACIENTE ID " + id);
        return modelMapper.map(paciente, PacienteDTO.class);
    }

    @Transactional
    public PacienteDTO salvar(PacienteDTO pacienteDTO) {
        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
        paciente = pacienteRepository.save(paciente);
        auditLogService.registrarAcao("CADASTRAR PACIENTE " + paciente.getId());

        // Enviar e-mail de confirmação
        String mensagem = "Olá " + paciente.getNome() + ",<br>Seu cadastro foi realizado com sucesso!";
        emailService.enviarEmail(paciente.getEmail(), "Cadastro Confirmado!", mensagem);

        return modelMapper.map(paciente, PacienteDTO.class);
    }

    @Transactional
    public PacienteDTO atualizar(Long id, PacienteDTO pacienteDTO) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(id);
        if (pacienteExistente.isEmpty()) {
            throw new RuntimeException("Paciente não encontrado");
        }
        Paciente pacienteAtualizado = modelMapper.map(pacienteDTO, Paciente.class);
        pacienteAtualizado.setId(id);
        pacienteRepository.save(pacienteAtualizado);
        auditLogService.registrarAcao("ATUALIZAR PACIENTE ID " + id);

        // Enviar e-mail de atualização
        String mensagem = "Olá " + pacienteAtualizado.getNome() + ",<br>Seus dados foram atualizados com sucesso!";
        emailService.enviarEmail(pacienteAtualizado.getEmail(), "Dados Atualizados!", mensagem);

        return modelMapper.map(pacienteAtualizado, PacienteDTO.class);
    }

    @Transactional
    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado");
        }
        pacienteRepository.deleteById(id);
        auditLogService.registrarAcao("DELETAR PACIENTE ID " + id);
    }
}
