package com.e_learning_system.dao;

import com.e_learning_system.entities.AnswersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswersEntity, Long> {
}
