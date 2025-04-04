package com.vidaplus.sghss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vidaplus.sghss.enums.UserRole;
import com.vidaplus.sghss.model.User;
import com.vidaplus.sghss.repository.UserRepository;

@Component
public class TestUserLoader implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("paciente@email.com").isEmpty()) {
            var user = new User();
            user.setEmail("paciente@email.com");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setNome("Paciente Teste");
            user.setRole(UserRole.PACIENTE);
            userRepository.save(user);
        }
    }
    
    
}

