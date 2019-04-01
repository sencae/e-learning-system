package com.e_learning_system.dao;

import com.e_learning_system.entities.CourseResources;
import com.e_learning_system.entities.ResourcesOfCourseEntity;

public interface DetachObject {
    void DetachResourcesOfCourse(ResourcesOfCourseEntity resourcesOfCourseEntity);
    void DetachCourseResources(CourseResources courseResources);
    void Clear();
}
