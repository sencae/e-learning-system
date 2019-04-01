package com.e_learning_system.dao;

import com.e_learning_system.entities.CourseResources;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.PersistenceContext;

public interface CourseResourcesRepository extends JpaRepository<CourseResources,Long> {
    int deleteByUrl(String url);

}
