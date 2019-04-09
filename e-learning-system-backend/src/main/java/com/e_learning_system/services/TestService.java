package com.e_learning_system.services;

import com.e_learning_system.dao.TestsRepository;
import com.e_learning_system.entities.TestsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final TestsRepository testsRepository;

    @Autowired
    public TestService(TestsRepository testsRepository) {
        this.testsRepository = testsRepository;
    }

    public TestsEntity createTest(TestsEntity testsEntity) {
        return testsRepository.saveAndFlush(testsEntity);
    }
}
