package com.e_learning_system.controllers;

import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.dto.TestDto;
import com.e_learning_system.entities.TestsEntity;
import com.e_learning_system.services.CoursesService;
import com.e_learning_system.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseGetController {
    private final ModelMapperUtil modelMapperUtil;
    private final TestService testService;
    private final CoursesService coursesService;

    @Autowired
    public TestController(ModelMapperUtil modelMapperUtil, TestService testService, CoursesService coursesService) {
        this.modelMapperUtil = modelMapperUtil;
        this.testService = testService;
        this.coursesService = coursesService;
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/createTest")
    public ResponseEntity<TestsEntity> createTest(@RequestBody TestDto testDto) {
        if (coursesService.getCourseById(testDto.getCourseId()).getTestsEntity() != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        TestsEntity testsEntity = modelMapperUtil.map(testDto, TestsEntity.class);
        testsEntity = testService.createTest(testsEntity);
        return new ResponseEntity<>(testsEntity, HttpStatus.OK);
    }
}
