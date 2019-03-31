package com.e_learning_system.controllers;

import com.e_learning_system.createCourse.service.CoursesService;
import com.e_learning_system.dto.CoursesDto;
import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.entities.Courses;
import com.e_learning_system.security.service.UserPrinciple;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class MyPageController extends BaseGetController {
    private final CoursesService coursesService;
    private final ModelMapperUtil modelMapperUtil;

    @Autowired
    public MyPageController(CoursesService coursesService, ModelMapperUtil modelMapperUtil) {
        this.coursesService = coursesService;
        this.modelMapperUtil = modelMapperUtil;
    }
    @GetMapping("/my")
    public ResponseEntity<List<CoursesDto>> getUserById() {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        List<Courses> list = coursesService.getCoursesByProfessorId(userPrinciple.getId());
        Type listType = new TypeToken<List<CoursesDto>>() {
        }.getType();
        List<CoursesDto> coursesDto = modelMapperUtil.map(list, listType);
        return new ResponseEntity<>(coursesDto, HttpStatus.OK);
    }
}
