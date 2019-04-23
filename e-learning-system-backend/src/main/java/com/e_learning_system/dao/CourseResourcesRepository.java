package com.e_learning_system.dao;

import com.e_learning_system.entities.CourseResources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseResourcesRepository extends JpaRepository<CourseResources,Long> {
    CourseResources getByUrl(String url);
    int deleteByUrl(String url);

}
