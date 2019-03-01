package com.e_learning_system.dao;

import com.e_learning_system.entities.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupsRepository extends JpaRepository<UserGroups, Long> {
    UserGroups getById(Long id);
}
