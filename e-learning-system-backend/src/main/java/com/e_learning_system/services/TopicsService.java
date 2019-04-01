package com.e_learning_system.services;

import com.e_learning_system.dao.TopicsRepository;
import com.e_learning_system.entities.TopicsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicsService {

    private final TopicsRepository topicsRepository;

    @Autowired
    public TopicsService(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }
    public TopicsEntity createTopic(TopicsEntity topicsEntity){
        return topicsRepository.saveAndFlush(topicsEntity);
    }
}
