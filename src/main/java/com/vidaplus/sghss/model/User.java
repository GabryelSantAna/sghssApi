package com.vidaplus.sghss.model;

import com.vidaplus.sghss.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@Schema(description = "Usuário do sistema")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nome do usuário", example = "João da Silva")
    private String nome;

    @Column(nullable = false, unique = true)
    @Schema(description = "E-mail do usuário", example = "joao@email.com")
    private String email;

    @Column(nullable = false)
    @Schema(description = "Senha criptografada do usuário")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Papel do usuário no sistema", example = "ADMIN")
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.name());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
