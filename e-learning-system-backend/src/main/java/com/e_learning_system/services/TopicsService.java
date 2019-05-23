package com.e_learning_system.services;

import com.e_learning_system.dao.TopicsRepository;
import com.e_learning_system.entities.TopicsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void deleteTopic(TopicsEntity topicsEntity) {
        topicsRepository.delete(topicsEntity);
    }

    public TopicsEntity save(TopicsEntity topicsEntity){
       return topicsRepository.save(topicsEntity);
    }
    public TopicsEntity getById(Long id) {
        return topicsRepository.getById(id);
    }
}
