package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.Consulta;
import com.vidaplus.sghss.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final ConsultaRepository consultaRepository;
    private final EmailService emailService;

    // 🔹 Agendamento diário às 8h da manhã
    @Scheduled(cron = "0 0 8 * * *")
    public void enviarLembretesConsulta() {
        LocalDate amanha = LocalDate.now().plusDays(1);
        LocalDateTime inicioDoDia = amanha.atStartOfDay();
        LocalDateTime fimDoDia = amanha.atTime(LocalTime.MAX);

        // 🔹 Buscar consultas do dia seguinte
        List<Consulta> consultas = consultaRepository.findByDataHoraBetween(inicioDoDia, fimDoDia);

        for (Consulta consulta : consultas) {
            enviarEmailLembrete(consulta);
        }
    }

    private void enviarEmailLembrete(Consulta consulta) {
        String assunto = "Lembrete: Consulta Médica Amanhã às " + consulta.getDataHora().toLocalTime();

        String mensagem = String.format(
                "Olá %s,<br><br>Este é um lembrete de que você tem uma consulta médica agendada amanhã às %s.<br>" +
                "Médico: %s<br>" +
                "Link para videochamada: <a href='%s'>Acesse aqui</a><br><br>" +
                "Atenciosamente, <br> SGHSS",
                consulta.getPaciente().getNome(),
                consulta.getDataHora().toLocalTime(),
                consulta.getMedico().getNome(),
                consulta.getLinkVideo()
        );

        // 🔹 Enviar e-mail para o paciente
        emailService.enviarEmail(consulta.getPaciente().getEmail(), assunto, mensagem);

        // 🔹 Enviar e-mail para o médico
        emailService.enviarEmail(consulta.getMedico().getEmail(), assunto, mensagem);
    }
}
