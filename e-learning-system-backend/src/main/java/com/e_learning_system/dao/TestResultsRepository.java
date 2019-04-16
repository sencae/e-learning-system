package com.e_learning_system.dao;

import com.e_learning_system.entities.TestResultsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TestResultsRepository extends JpaRepository<TestResultsEntity, Long> {
    Set<TestResultsEntity> findByTestId(Long testId);
}
