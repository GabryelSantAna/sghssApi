package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.enums.UserRole;
import com.vidaplus.sghss.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	long countByRole(UserRole role);
	Optional<User> findByEmail(String email);
}
