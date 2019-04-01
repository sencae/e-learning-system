package com.e_learning_system.controllers;

import com.e_learning_system.entities.UsersOnCoursesEntity;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.CoursesService;
import com.e_learning_system.dto.CoursesDto;
import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.entities.Courses;
import com.e_learning_system.services.UserOnCoursesService;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class CoursesGetController extends BaseGetController {
    private static final Logger logger = LoggerFactory.getLogger(CoursesGetController.class);
    private final CoursesService coursesService;
    private final ModelMapperUtil modelMapperUtil;

    private final UserOnCoursesService userOnCoursesService;

    @Autowired
    public CoursesGetController(CoursesService coursesService, ModelMapperUtil modelMapperUtil, UserOnCoursesService userOnCoursesService) {
        this.coursesService = coursesService;
        this.modelMapperUtil = modelMapperUtil;
        this.userOnCoursesService = userOnCoursesService;
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
    public ResponseEntity<CoursesDto> getCourseById(@PathVariable("id") long id) {
        Courses course = coursesService.getCourseById(id);
        CoursesDto coursesDto = modelMapperUtil.map(course, CoursesDto.class);
        return new ResponseEntity<>(coursesDto, HttpStatus.OK);
    }

    @PostMapping("check")
    public ResponseEntity<Boolean> checkUser(@RequestBody Long courseId) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        UsersOnCoursesEntity usersOnCoursesEntity = new UsersOnCoursesEntity(userPrinciple.getId(), courseId);
        try {
            boolean response = userOnCoursesService.checkUser(usersOnCoursesEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error. Message - {}", ex.getMessage());
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("isauthor")
    public ResponseEntity<Boolean> isAuthor(@RequestBody Long courseId) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        try {
            boolean response = userPrinciple.getId().equals(coursesService.getCourseById(courseId).getProfessorId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error. Message - {}", ex.getMessage());
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
