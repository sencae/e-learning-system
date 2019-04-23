package com.e_learning_system.services;

import com.e_learning_system.dao.CourseResourcesRepository;
import com.e_learning_system.dao.CoursesRep;
import com.e_learning_system.dto.CoursesDto;
import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.entities.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoursesService {
    private final CoursesRep coursesRep;
    private final CourseResourcesRepository courseResourcesRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Autowired
    public CoursesService(CoursesRep coursesRep,
                          CourseResourcesRepository courseResourcesRepository, ModelMapperUtil modelMapperUtil) {
        this.coursesRep = coursesRep;
        this.courseResourcesRepository = courseResourcesRepository;
        this.modelMapperUtil = modelMapperUtil;
    }

    @Transactional
    public boolean deleteResource(String url) {
        return this.courseResourcesRepository.deleteByUrl(url) > 0;
    }

    public void saveCourse(Courses course) {
        coursesRep.saveAndFlush(course);
    }

    public List<Courses> getAllCourses() {
        return coursesRep.findAll();
    }

    public List<Courses> getCoursesByProfessorId(Long professorId) {
        return coursesRep.findAllByProfessorId(professorId);
    }

    public Courses getCourseById(Long id) {
        return coursesRep.getById(id);
    }

    public Courses getCourseByUrl(String url) {
        return coursesRep.getByUrl(url);
    }

    public CoursesDto updateCourse(Long userId, CoursesDto courseDto) {
        Courses course = coursesRep.getById(courseDto.getId());
        if (course.getProfessorId().equals(userId)) {
            modelMapperUtil.map(courseDto, course);
            course.setProfessorId(userId);
            return modelMapperUtil.map(coursesRep.saveAndFlush(course), CoursesDto.class);
        } else {
            return null;
        }
    }

}
