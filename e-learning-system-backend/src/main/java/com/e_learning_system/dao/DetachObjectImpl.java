package com.e_learning_system.dao;

import com.e_learning_system.entities.CourseResources;
import com.e_learning_system.entities.ResourcesOfCourseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DetachObjectImpl implements DetachObject {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void DetachResourcesOfCourse(ResourcesOfCourseEntity resourcesOfCourseEntity) {
        entityManager.detach(resourcesOfCourseEntity);
    }

    @Override
    public void DetachCourseResources(CourseResources courseResources) {
        entityManager.detach(courseResources);
    }

    @Override
    public void Clear() {
        entityManager.clear();
    }
}
