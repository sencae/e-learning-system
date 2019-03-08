package com.e_learning_system.controllers;

import com.e_learning_system.createCourse.service.CoursesService;
import com.e_learning_system.dto.CoursesDto;
import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.entities.Courses;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class CoursesGetController extends BaseGetController {
    private final CoursesService coursesService;
    private final ModelMapperUtil modelMapperUtil;

    @Autowired
    public CoursesGetController(CoursesService coursesService, ModelMapperUtil modelMapperUtil) {
        this.coursesService = coursesService;
        this.modelMapperUtil = modelMapperUtil;
    }
    @GetMapping("/courses/all")
    public ResponseEntity<List<CoursesDto>> getAllCourses() {
        List<Courses> list = coursesService.getAllCourses();
        Type listType = new TypeToken<List<CoursesDto>>() {
        }.getType();
        List<CoursesDto> coursesDto = modelMapperUtil.map(list, listType);
        return new ResponseEntity<>(coursesDto, HttpStatus.OK);
    }
    @GetMapping("/course/{id}")
    public ResponseEntity<CoursesDto> getUserById(@PathVariable("id") long id) {
        Courses course = coursesService.getCourseById(id);
        CoursesDto coursesDto = modelMapperUtil.map(course,CoursesDto.class);
        return new ResponseEntity<>(coursesDto, HttpStatus.OK);
    }
}
