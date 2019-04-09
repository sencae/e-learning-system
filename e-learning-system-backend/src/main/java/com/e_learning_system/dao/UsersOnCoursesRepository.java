package com.e_learning_system.dao;

import com.e_learning_system.entities.UsersOnCoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UsersOnCoursesRepository extends JpaRepository<UsersOnCoursesEntity,Long> {
    List<UsersOnCoursesEntity> getByCourseIdAndUserId(Long courseId, Long UserId);

    Set<UsersOnCoursesEntity> getByCourseId(Long courseId);
}
