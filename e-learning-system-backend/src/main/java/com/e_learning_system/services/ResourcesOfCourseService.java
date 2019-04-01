package com.e_learning_system.services;

import com.e_learning_system.dao.ResourcesOfCourseRepository;
import com.e_learning_system.entities.ResourcesOfCourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourcesOfCourseService {
    private final ResourcesOfCourseRepository resourcesOfCourseRepository;

    @Autowired
    public ResourcesOfCourseService(ResourcesOfCourseRepository resourcesOfCourseRepository) {
        this.resourcesOfCourseRepository = resourcesOfCourseRepository;
    }
    public ResourcesOfCourseEntity save(ResourcesOfCourseEntity resourcesOfCourseEntity){
       return resourcesOfCourseRepository.save(resourcesOfCourseEntity);
    }
}
