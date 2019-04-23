package com.e_learning_system.dao;

import com.e_learning_system.entities.TopicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<TopicsEntity,Long> {
    TopicsEntity getById(Long id);
}
