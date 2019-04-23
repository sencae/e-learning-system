package com.e_learning_system.services;

import com.e_learning_system.dao.CourseResourcesRepository;
import com.e_learning_system.entities.CourseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourcesService {
    private final CourseResourcesRepository courseResourcesRepository;

    @Autowired
    public ResourcesService(CourseResourcesRepository courseResourcesRepository) {

        this.courseResourcesRepository = courseResourcesRepository;
    }

    public CourseResources getResourceByUrl(String url) {
        return courseResourcesRepository.getByUrl(url);
    }

    public CourseResources saveResource(CourseResources courseResources) {
      return courseResourcesRepository.saveAndFlush(courseResources);

    }

}
