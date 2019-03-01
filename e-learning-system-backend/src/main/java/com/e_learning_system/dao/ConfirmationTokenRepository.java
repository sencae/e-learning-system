package com.e_learning_system.dao;

import com.e_learning_system.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken getByConfirmationToken(String confirmationToken);
}
