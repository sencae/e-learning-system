package com.e_learning_system.controllers;

import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.dto.StudentManageDto;
import com.e_learning_system.entities.User;
import com.e_learning_system.services.UserOnCoursesService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.Set;

@RestController
public class CourseManageController extends BaseGetController {
    private final UserOnCoursesService userOnCoursesService;
    private final ModelMapperUtil modelMapperUtil;

    @Autowired
    public CourseManageController(UserOnCoursesService userOnCoursesService, ModelMapperUtil modelMapperUtil) {
        this.userOnCoursesService = userOnCoursesService;
        this.modelMapperUtil = modelMapperUtil;
    }

    @PostMapping("/usersoncourse")
    public ResponseEntity<Set<StudentManageDto>> getStudents(@RequestBody Long courseId) {
        Set<User> f = userOnCoursesService.getUsersByCourseId(courseId);
        Type setType = new TypeToken<Set<StudentManageDto>>() {
        }.getType();
        Set<StudentManageDto> dto = modelMapperUtil.map(f, setType);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
