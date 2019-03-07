package com.e_learning_system.createCourse.controller;

import com.e_learning_system.createCourse.service.CoursesService;
import com.e_learning_system.entities.Courses;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.dto.CoursesDto;
import com.e_learning_system.dto.ModelMapperUtil;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("courses")
public class CoursesController {
    private final CoursesService coursesService;
    private final ModelMapperUtil modelMapperUtil;

    @Autowired
    public CoursesController(CoursesService coursesService, ModelMapperUtil modelMapperUtil) {
        this.coursesService = coursesService;
        this.modelMapperUtil = modelMapperUtil;
    }

    @GetMapping("alls")
    public ResponseEntity<List<CoursesDto>> getAllCourses() {
        List<Courses> list = coursesService.getAllCourses();
        Type listType = new TypeToken<List<CoursesDto>>() {
        }.getType();
        List<CoursesDto> coursesDto = modelMapperUtil.map(list, listType);
        return new ResponseEntity<>(coursesDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("create")
    public ResponseEntity<Void> createCourse(@RequestBody Courses course) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        course.setProfessorId(userPrinciple.getId());
        coursesService.createCourse(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
