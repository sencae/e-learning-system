package com.e_learning_system.dao;

import com.e_learning_system.entities.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionsEntity, Long> {
}
