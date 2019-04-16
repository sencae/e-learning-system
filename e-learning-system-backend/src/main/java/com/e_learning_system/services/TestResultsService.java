package com.e_learning_system.services;

import com.e_learning_system.dao.TestResultsRepository;
import com.e_learning_system.entities.TestResultsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

@Service
public class TestResultsService {
    private final TestResultsRepository testResultsRepository;

    @Autowired
    public TestResultsService(TestResultsRepository testResultsRepository) {
        this.testResultsRepository = testResultsRepository;
    }

    public TestResultsEntity saveResults(Long userId, Timestamp date, Short result, Long testId) {
        TestResultsEntity testResultsEntity = new TestResultsEntity();
        testResultsEntity.setDate(date);
        testResultsEntity.setUserId(userId);
        testResultsEntity.setResult(result);
        testResultsEntity.setTestId(testId);
        return testResultsRepository.save(testResultsEntity);
    }

    public Set<TestResultsEntity> findByTestId(Long testId) {
        return testResultsRepository.findByTestId(testId);
    }
}
