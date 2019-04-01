package com.e_learning_system.dao;

import com.e_learning_system.entities.UsersOnCoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersOnCoursesRepository extends JpaRepository<UsersOnCoursesEntity,Long> {
    int getByCourseIdAndUserId(Long courseId,Long UserId);
}
