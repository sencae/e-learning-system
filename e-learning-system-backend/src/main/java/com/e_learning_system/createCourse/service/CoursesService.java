package com.e_learning_system.createCourse.service;

import com.e_learning_system.dao.CoursesDao;
import com.e_learning_system.entities.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {

    private final CoursesDao coursesDao;

    @Autowired
    public CoursesService(CoursesDao coursesDao) {
        this.coursesDao = coursesDao;
    }

    public void createCourse(Courses course) {
        coursesDao.add(course);
    }

    public List<?> getAllCourses() {
        return coursesDao.getAll();
    }
}
