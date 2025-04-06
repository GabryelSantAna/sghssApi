package com.vidaplus.sghss.config;

import com.vidaplus.sghss.enums.UserRole;
import com.vidaplus.sghss.model.User;
import com.vidaplus.sghss.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SuperAdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createSuperAdmin() {
        return args -> {
            if (userRepository.findByRole(UserRole.SUPER_ADMIN).isEmpty()) {
                User superAdmin = new User();
                superAdmin.setNome("Super Admin");
                superAdmin.setEmail("superadmin@vidaplus.com");
                superAdmin.setPassword(passwordEncoder.encode("admin123")); // 🔹 Senha segura
                superAdmin.setRole(UserRole.SUPER_ADMIN);

                userRepository.save(superAdmin);
                System.out.println("✅ SUPER_ADMIN criado com sucesso!");
            }
        };
    }
}
