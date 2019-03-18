package com.e_learning_system.createCourse.service;

import com.e_learning_system.dao.CourseResourcesRepository;
import com.e_learning_system.dao.CoursesRep;
import com.e_learning_system.entities.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoursesService {
    private final CoursesRep coursesRep;
    private final CourseResourcesRepository courseResourcesRepository;

    @Autowired
    public CoursesService(CoursesRep coursesRep,
                          CourseResourcesRepository courseResourcesRepository) {
        this.coursesRep = coursesRep;
        this.courseResourcesRepository = courseResourcesRepository;
    }
    @Transactional
    public boolean deleteResource(String url){
        return this.courseResourcesRepository.deleteByUrl(url)>0;
    }
    public void createCourse(Courses course) {
        coursesRep.save(course);
    }

    public List<Courses> getAllCourses() {
        return coursesRep.findAll();
    }

    public Courses getCourseById(Long id){ return coursesRep.getById(id);}
}
