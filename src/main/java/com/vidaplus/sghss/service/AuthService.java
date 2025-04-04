package com.vidaplus.sghss.service;

import com.vidaplus.sghss.dto.AuthRequest;
import com.vidaplus.sghss.dto.AuthResponse;
import com.vidaplus.sghss.dto.UserRegisterRequest;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.model.User;
import com.vidaplus.sghss.enums.UserRole;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.repository.UserRepository;
import com.vidaplus.sghss.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(UserRegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        User newUser = new User();
        newUser.setNome(request.getNome());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());

        userRepository.save(newUser);

        // Se for um paciente, cria um registro na tabela pacientes
        if (request.getRole() == UserRole.PACIENTE) {
            Paciente paciente = new Paciente();
            paciente.setNome(request.getNome());
            paciente.setEmail(request.getEmail());
            paciente.setCpf(request.getCpf()); // Se necessário
            paciente.setDataNascimento(request.getDataNascimento()); // Se necessário
            paciente.setEndereco(request.getEndereco()); // Se necessário
            paciente.setTelefone(request.getTelefone()); // Se necessário
            pacienteRepository.save(paciente);
        }

        String token = jwtUtil.generateToken(newUser.getEmail());

        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}
