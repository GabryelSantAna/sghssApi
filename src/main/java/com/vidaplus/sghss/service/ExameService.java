package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.Exame;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.repository.ExameRepository;
import com.vidaplus.sghss.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExameService {

    private final ExameRepository exameRepository;
    private final PacienteRepository pacienteRepository;
    private final FileStorageService fileStorageService;  // 🔹 Usando armazenamento local

    public List<Exame> listarExamesPorPaciente(Long pacienteId) {
        return exameRepository.findByPacienteId(pacienteId);
    }
    
    @Transactional
    public Exame registrarExame(Long pacienteId, MultipartFile file) throws IOException {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        String nomeArquivo = file.getOriginalFilename();
        String urlArquivo = fileStorageService.salvarArquivo(file);  // 🔹 Salva localmente

        Exame exame = new Exame();
        exame.setPaciente(paciente);
        exame.setNomeArquivo(nomeArquivo);
        exame.setUrlArquivo(urlArquivo);
        exame.setDataEnvio(LocalDateTime.now());

        return exameRepository.save(exame);
    }
}
