package com.e_learning_system.dao;

import com.e_learning_system.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRep extends JpaRepository<Courses, Long> {
    Courses getByProfessorId(Long professor_id);
}
