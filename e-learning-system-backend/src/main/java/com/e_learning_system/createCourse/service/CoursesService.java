package com.e_learning_system.createCourse.service;

import com.e_learning_system.dao.CoursesRep;
import com.e_learning_system.entities.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {
    private final CoursesRep coursesRep;

    @Autowired
    public CoursesService(CoursesRep coursesRep) {
        this.coursesRep = coursesRep;
    }

    public void createCourse(Courses course) {
        coursesRep.save(course);
    }

    public List<Courses> getAllCourses() {
        return coursesRep.findAll();
    }

    public Courses getCourseById(Long id){ return coursesRep.getById(id);}
}
