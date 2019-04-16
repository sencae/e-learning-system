package com.e_learning_system.services;

import com.e_learning_system.dao.QuestionRepository;
import com.e_learning_system.entities.QuestionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionsEntity> saveQuestions(List<QuestionsEntity> questionsEntities) {
        return questionRepository.saveAll(questionsEntities);
    }

    public List<QuestionsEntity> getAll() {
        return questionRepository.findAll();
    }
}
