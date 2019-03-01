package com.e_learning_system.dao;

import com.e_learning_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);

    User getUserByEmail(String email);

    default boolean getUserByEmaila(String email) {
        return this.getUserByEmail(email) != null;
    }

}
