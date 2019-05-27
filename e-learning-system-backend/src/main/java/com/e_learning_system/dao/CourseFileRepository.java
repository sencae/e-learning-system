package com.e_learning_system.dao;

import com.e_learning_system.entities.CourseFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseFileRepository extends JpaRepository<CourseFile,Long> {
    CourseFile getByUrl(String url);
}
