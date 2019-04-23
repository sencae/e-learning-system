package com.e_learning_system.controllers;

import com.e_learning_system.dto.CoursesDto;
import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.entities.Courses;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.CoursesService;
import com.e_learning_system.services.registrationService.UserService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class MyPageController extends BaseGetController {
    private final CoursesService coursesService;
    private final ModelMapperUtil modelMapperUtil;
    private final UserService userService;
    @Autowired
    public MyPageController(CoursesService coursesService, ModelMapperUtil modelMapperUtil, UserService userService) {
        this.coursesService = coursesService;
        this.modelMapperUtil = modelMapperUtil;
        this.userService = userService;
    }
    @PreAuthorize("hasAnyAuthority('professor','student')")
    @GetMapping("/my")
    public ResponseEntity<List<CoursesDto>> getUserById() {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        boolean hasAuthority = userPrinciple.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("professor"));
        if(hasAuthority) {
            List<Courses> courses = coursesService.getCoursesByProfessorId(userPrinciple.getId());
            Type listType = new TypeToken<List<CoursesDto>>() {
            }.getType();
            List<CoursesDto> coursesDto = modelMapperUtil.map(courses, listType);
            return new ResponseEntity<>(coursesDto, HttpStatus.OK);
        }else {
            List<Courses> courses = userService.getUserById(userPrinciple.getId()).getCoursesList();
            Type listType = new TypeToken<List<CoursesDto>>() {
            }.getType();
            List<CoursesDto> coursesDto = modelMapperUtil.map(courses, listType);
            return new ResponseEntity<>(coursesDto, HttpStatus.OK);
        }
    }
}
