package com.india.railway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.india.railway.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}