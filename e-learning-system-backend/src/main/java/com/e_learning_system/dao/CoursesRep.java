package com.e_learning_system.dao;

import com.e_learning_system.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRep extends JpaRepository<Courses, Long> {
    Courses getById(Long id);
    List<Courses> findAllByProfessorId(long professorID);

    Courses getByUrl(String url);
}
