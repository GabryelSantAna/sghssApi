package com.vidaplus.sghss.service;

import com.vidaplus.sghss.repository.ConsultaRepository;
import com.vidaplus.sghss.repository.ExameRepository;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.repository.UserRepository;
import com.vidaplus.sghss.enums.StatusConsulta;
import com.vidaplus.sghss.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final UserRepository userRepository;
    private final ExameRepository exameRepository;

    public Map<String, Object> obterEstatisticas() {
        Map<String, Object> estatisticas = new HashMap<>();

        estatisticas.put("totalPacientes", pacienteRepository.count());
        estatisticas.put("totalMedicos", userRepository.countByRole(UserRole.MEDICO));
        estatisticas.put("totalConsultas", consultaRepository.count());
        estatisticas.put("consultasAgendadas", consultaRepository.countByStatus(StatusConsulta.AGENDADA));
        estatisticas.put("consultasRealizadas", consultaRepository.countByStatus(StatusConsulta.REALIZADA));
        estatisticas.put("consultasCanceladas", consultaRepository.countByStatus(StatusConsulta.CANCELADA));

        // 🔹 Total de exames enviados
        estatisticas.put("totalExames", exameRepository.count());

        // 🔹 Encontrar os horários mais agendados
        List<LocalTime> horarios = consultaRepository.findAll().stream()
                .map(consulta -> consulta.getDataHora().toLocalTime())
                .collect(Collectors.toList());

        Map<LocalTime, Long> frequenciaHorarios = horarios.stream()
                .collect(Collectors.groupingBy(h -> h, Collectors.counting()));

        estatisticas.put("horariosMaisAgendados", frequenciaHorarios);

        return estatisticas;
    }
}
