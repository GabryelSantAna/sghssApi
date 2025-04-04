package com.vidaplus.sghss.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.vidaplus.sghss.enums.StatusConsulta;
import com.vidaplus.sghss.enums.StatusLeito;
import com.vidaplus.sghss.enums.UserRole;
import com.vidaplus.sghss.repository.ConsultaRepository;
import com.vidaplus.sghss.repository.ExameRepository;
import com.vidaplus.sghss.repository.LeitoRepository;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final PacienteRepository pacienteRepository;
    private final ConsultaRepository consultaRepository;
    private final ExameRepository exameRepository;
    private final UserRepository userRepository;
    private final LeitoRepository leitoRepository;
   
  
    
    public byte[] gerarRelatorioAdministrativo() {
        long totalPacientes = pacienteRepository.count();
        long totalMedicos = userRepository.countByRole(UserRole.MEDICO);
        long totalConsultas = consultaRepository.count();
        long consultasAgendadas = consultaRepository.countByStatus(StatusConsulta.AGENDADA);
        long consultasRealizadas = consultaRepository.countByStatus(StatusConsulta.REALIZADA);
        long consultasCanceladas = consultaRepository.countByStatus(StatusConsulta.CANCELADA);
        long totalExames = exameRepository.count();
        long leitosDisponiveis = leitoRepository.countByStatus(StatusLeito.DISPONIVEL);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Relatório Administrativo - SGHSS"));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("🔹 Estatísticas Gerais"));
        document.add(new Paragraph("Total de Pacientes: " + totalPacientes));
        document.add(new Paragraph("Total de Médicos: " + totalMedicos));
        document.add(new Paragraph("Total de Consultas: " + totalConsultas));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("🔹 Consultas"));
        document.add(new Paragraph("Agendadas: " + consultasAgendadas));
        document.add(new Paragraph("Realizadas: " + consultasRealizadas));
        document.add(new Paragraph("Canceladas: " + consultasCanceladas));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("🔹 Exames e Leitos"));
        document.add(new Paragraph("Total de Exames Enviados: " + totalExames));
        document.add(new Paragraph("Leitos Disponíveis: " + leitosDisponiveis));

        document.close();
        return outputStream.toByteArray();
    }
}
