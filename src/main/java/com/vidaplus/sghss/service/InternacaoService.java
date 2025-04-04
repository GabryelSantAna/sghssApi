package com.vidaplus.sghss.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vidaplus.sghss.enums.StatusLeito;
import com.vidaplus.sghss.model.Internacao;
import com.vidaplus.sghss.model.Leito;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.repository.InternacaoRepository;
import com.vidaplus.sghss.repository.LeitoRepository;
import com.vidaplus.sghss.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InternacaoService {

	private final LogAuditoriaService logService;
    private final InternacaoRepository internacaoRepository;
    private final PacienteRepository pacienteRepository;
    private final LeitoRepository leitoRepository;
    private final EmailService emailService;
    
    @Transactional
    public Internacao internarPaciente(Long pacienteId, Long leitoId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Leito leito = leitoRepository.findById(leitoId)
                .orElseThrow(() -> new RuntimeException("Leito não encontrado"));

        if (leito.getStatus() == StatusLeito.OCUPADO) {
            throw new RuntimeException("Leito já está ocupado!");
        }

        leito.setStatus(StatusLeito.OCUPADO);
        leitoRepository.save(leito);

        Internacao internacao = new Internacao();
        internacao.setPaciente(paciente);
        internacao.setLeito(leito);
        internacao.setDataEntrada(LocalDateTime.now());

        Internacao novaInternacao = internacaoRepository.save(internacao);

        // 🔹 Enviar e-mail ao paciente
        String mensagem = String.format(
                "<h3>Olá %s,</h3>" +
                "<p>Você foi internado no leito %d.</p>" +
                "<p><strong>Data de Entrada:</strong> %s</p>" +
                "<p>Atenciosamente, <br> SGHSS</p>",
                paciente.getNome(), leito.getNumero(), internacao.getDataEntrada()
        );

        emailService.enviarEmail(paciente.getEmail(), "Confirmação de Internação", mensagem);
        logService.registrarLog("Paciente " + paciente.getNome() + " internado no leito " + leito.getNumero());
        return novaInternacao;
    }

    @Transactional
    public void darAlta(Long internacaoId) {
        Internacao internacao = internacaoRepository.findById(internacaoId)
                .orElseThrow(() -> new RuntimeException("Internação não encontrada"));

        internacao.setDataSaida(LocalDateTime.now());

        Leito leito = internacao.getLeito();
        leito.setStatus(StatusLeito.DISPONIVEL);
        leitoRepository.save(leito);

        internacaoRepository.save(internacao);

        // 🔹 Enviar e-mail ao paciente
        String mensagem = String.format(
                "<h3>Olá %s,</h3>" +
                "<p>Você recebeu alta do hospital.</p>" +
                "<p><strong>Data da Alta:</strong> %s</p>" +
                "<p>Atenciosamente, <br> SGHSS</p>",
                internacao.getPaciente().getNome(), internacao.getDataSaida()
        );

        emailService.enviarEmail(internacao.getPaciente().getEmail(), "Confirmação de Alta", mensagem);
        logService.registrarLog("Paciente " + internacao.getPaciente().getNome() + " recebeu alta do leito " + leito.getNumero());

    }
}
