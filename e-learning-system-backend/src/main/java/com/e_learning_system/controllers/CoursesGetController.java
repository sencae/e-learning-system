package com.e_learning_system.controllers;

import com.e_learning_system.dto.CoursesDto;
import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.entities.Courses;
import com.e_learning_system.entities.User;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.CoursesService;
import com.e_learning_system.services.UserOnCoursesService;
import com.e_learning_system.services.registrationService.UserService;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class CoursesGetController extends BaseGetController {
    private static final Logger logger = LoggerFactory.getLogger(CoursesGetController.class);
    private final CoursesService coursesService;
    private final ModelMapperUtil modelMapperUtil;

    private final UserOnCoursesService userOnCoursesService;
    private final UserService userService;

    @Autowired
    public CoursesGetController(CoursesService coursesService, ModelMapperUtil modelMapperUtil, UserOnCoursesService userOnCoursesService, UserService userService) {
        this.coursesService = coursesService;
        this.modelMapperUtil = modelMapperUtil;
        this.userOnCoursesService = userOnCoursesService;
        this.userService = userService;
    }

    @GetMapping("/courses/all")
    public ResponseEntity<List<CoursesDto>> getAllCourses() {
        if (SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal() == "anonymousUser") {
            List<Courses> list = coursesService.getAllCourses();
            Type listType = new TypeToken<List<CoursesDto>>() {
            }.getType();
            List<CoursesDto> coursesDto = modelMapperUtil.map(list, listType);
            return new ResponseEntity<>(coursesDto, HttpStatus.OK);
        } else {

            UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            User user = userService.getUserById(userPrinciple.getId());
            List<Courses> list = coursesService.getAllCourses();
            Type listType = new TypeToken<List<CoursesDto>>() {
            }.getType();
            List<CoursesDto> coursesDto = modelMapperUtil.map(list, listType);
            for (Courses courses : list) {
                for (CoursesDto dto : coursesDto) {
                    if (dto.getId().equals(courses.getId())) {
                        if (user.getCoursesList().contains(courses))
                            coursesDto.get(coursesDto.indexOf(dto)).setJoin(true);
                        if (coursesDto.get(coursesDto.indexOf(dto)).getProfessorId() == user.getId())
                            coursesDto.get(coursesDto.indexOf(dto)).setAuthor(true);
                    }
                }
            }
            return new ResponseEntity<>(coursesDto, HttpStatus.OK);
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CoursesDto> getCourseById(@PathVariable("id") long courseId) {
        if (courseId < 0)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            Courses course = coursesService.getCourseById(courseId);
            CoursesDto coursesDto = modelMapperUtil.map(course, CoursesDto.class);
            coursesDto.setStarted(coursesDto.getStartDate().getTime() < System.currentTimeMillis());
            if (SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal() == "anonymousUser") {
                coursesDto.setJoin(false);
                coursesDto.setTopics(null);
                coursesDto.setTest(null);
                return new ResponseEntity<>(coursesDto, HttpStatus.OK);
            }
            UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            coursesDto.setJoin(userOnCoursesService.checkUser(userPrinciple.getId(), courseId));
            coursesDto.setAuthor(userPrinciple.getId().equals(course.getProfessorId()));
            if (coursesDto.isAuthor() || (coursesDto.isJoin() && coursesDto.isStarted()))
                return new ResponseEntity<>(coursesDto, HttpStatus.OK);
            else {
                coursesDto.setTopics(null);
                return new ResponseEntity<>(coursesDto, HttpStatus.OK);
            }

        } catch (Exception ex) {
            logger.error("Error. Message - {}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
