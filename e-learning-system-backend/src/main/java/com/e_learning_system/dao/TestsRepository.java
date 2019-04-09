package com.e_learning_system.dao;

import com.e_learning_system.entities.TestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestsRepository extends JpaRepository<TestsEntity, Long> {
}
