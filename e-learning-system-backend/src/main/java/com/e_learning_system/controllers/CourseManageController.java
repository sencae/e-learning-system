package com.e_learning_system.controllers;

import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.dto.StudentManageDto;
import com.e_learning_system.entities.TestResultsEntity;
import com.e_learning_system.entities.User;
import com.e_learning_system.entities.UsersOnCoursesEntity;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.TestResultsService;
import com.e_learning_system.services.UserOnCoursesService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.Set;

@RestController
public class CourseManageController extends BaseGetController {
    private final UserOnCoursesService userOnCoursesService;
    private final ModelMapperUtil modelMapperUtil;
    private final TestResultsService testResultsService;

    @Autowired
    public CourseManageController(UserOnCoursesService userOnCoursesService, ModelMapperUtil modelMapperUtil, TestResultsService testResultsService) {
        this.userOnCoursesService = userOnCoursesService;
        this.modelMapperUtil = modelMapperUtil;
        this.testResultsService = testResultsService;
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/usersoncourse")
    public ResponseEntity<Set<StudentManageDto>> getStudents(@RequestBody Long courseId) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Set<User> f = userOnCoursesService.getUsersByCourseId(courseId, userPrinciple.getId());
        if (f == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        Set<TestResultsEntity> testResultsEntities = testResultsService.findByTestId(courseId);
        Type setType = new TypeToken<Set<StudentManageDto>>() {
        }.getType();
        Set<StudentManageDto> dto = modelMapperUtil.map(f, setType);
        for (StudentManageDto studentManageDto : dto) {
            for (TestResultsEntity testResultsEntity : testResultsEntities) {
                if (studentManageDto.getUserId().equals(testResultsEntity.getUserId()))
                    studentManageDto.setTestResults(testResultsEntity);
                studentManageDto.setTestName(testResultsEntity.getTestsEntity().getTestName());
            }
            for(User user: f){
                if(studentManageDto.getUserId().equals(user.getId())){
                    user.getUsersOnCoursesEntities().forEach(course->{
                        if(course.getCourseId().equals(courseId)){
                            studentManageDto.setFinished(course.getFinished());
                            studentManageDto.setTaskUrl(course.getCheckUrl());
                        }
                    });
                }
            }
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("endCourse")
    public ResponseEntity<Void> endCourse(@RequestBody ObjectNode objectNode ){
        UsersOnCoursesEntity usersOnCoursesEntity = userOnCoursesService.getUserOnCourseByUserIdAndCourseId(objectNode.get("studentId").asLong(),
                objectNode.get("courseId").asLong());
        usersOnCoursesEntity.setFinished(true);
        userOnCoursesService.save(usersOnCoursesEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("deletefromcourse")
    public ResponseEntity<Void> deleteFromCourse(@RequestBody ObjectNode objectNode ){
        UsersOnCoursesEntity usersOnCoursesEntity = userOnCoursesService.getUserOnCourseByUserIdAndCourseId(objectNode.get("studentId").asLong(),
                objectNode.get("courseId").asLong());
        userOnCoursesService.delete(usersOnCoursesEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('student')")
    @PostMapping("endCourseBut")
    public ResponseEntity<Void> endCourse(@RequestBody Long courseId){
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        UsersOnCoursesEntity usersOnCoursesEntity = userOnCoursesService.getUserOnCourseByUserIdAndCourseId(userPrinciple.getId(),
                courseId);
        usersOnCoursesEntity.setFinished(true);
        userOnCoursesService.save(usersOnCoursesEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
