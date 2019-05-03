package com.e_learning_system.services;

import com.e_learning_system.dao.TestsRepository;
import com.e_learning_system.entities.TestsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public TestsEntity getTest(Long id) {
        return testsRepository.getOne(id);
    }

    public TestsEntity save(TestsEntity testsEntity) {
        return testsRepository.saveAndFlush(testsEntity);
    }

    @Transactional
    public void deleteTestById(Long id) {
        testsRepository.deleteById(id);
    }
}
