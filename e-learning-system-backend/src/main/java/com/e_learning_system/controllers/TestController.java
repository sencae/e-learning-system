package com.e_learning_system.controllers;

import com.e_learning_system.dto.*;
import com.e_learning_system.entities.*;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.CoursesService;
import com.e_learning_system.services.QuestionService;
import com.e_learning_system.services.TestResultsService;
import com.e_learning_system.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class TestController extends BaseGetController {
    private final ModelMapperUtil modelMapperUtil;
    private final TestService testService;
    private final CoursesService coursesService;
    private final QuestionService questionService;
    private final TestResultsService testResultsService;
    @Autowired
    public TestController(ModelMapperUtil modelMapperUtil, TestService testService, CoursesService coursesService, QuestionService questionService, TestResultsService testResultsService) {
        this.modelMapperUtil = modelMapperUtil;
        this.testService = testService;
        this.coursesService = coursesService;
        this.questionService = questionService;
        this.testResultsService = testResultsService;
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/createTest")
    public ResponseEntity<TestsEntity> createTest(@RequestBody CreateTestDto createTestDto) {
        if (createTestDto.getCourseId() < 0)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        if (coursesService.getCourseById(createTestDto.getCourseId()).getTestsEntity() != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        TestsEntity testsEntity = modelMapperUtil.map(createTestDto, TestsEntity.class);
        testsEntity = testService.createTest(testsEntity);
        return new ResponseEntity<>(testsEntity, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/getTestEdit")
    public ResponseEntity<TestsEntity> getTestEdit(@RequestBody Long courseId) {
        if (courseId < 0)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(coursesService.getCourseById(courseId).getTestsEntity(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('student','professor')")
    @PostMapping("/getTest")
    public ResponseEntity<TestDto> getTest(@RequestBody Long courseId) {
        if (courseId < 0)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        TestDto testDto = modelMapperUtil.map(coursesService.getCourseById(courseId).getTestsEntity(), TestDto.class);
        testDto.getQuestions().forEach(questionDto -> questionDto.getAnswers().forEach(answerDto -> answerDto.setCorrectAnswer(false)));
        return new ResponseEntity<>(testDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/saveTest")
    public ResponseEntity<Void> saveTest(@RequestBody TestsEntity test) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (coursesService.getCourseById(test.getId()).getProfessorId().equals(userPrinciple.getId())) {
            testService.save(test);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("hasAuthority('student')")
    @PostMapping("completeTest")
    public ResponseEntity<Short> completeTest(@RequestBody TestDto testDto) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        TestsEntity testsEntity = testService.getTest(testDto.getId());
        double grade = 0.;
        int maxGrade = testsEntity.getQuestions().size();
        for (QuestionsEntity qe : testsEntity.getQuestions()) {
            for (QuestionDto qd : testDto.getQuestions()) {
                if (qe.getId().equals(qd.getId())) {
                    for (AnswersEntity ae : qe.getAnswers())
                        for (AnswerDto ad : qd.getAnswers()) {
                            if (ae.getId().equals(ad.getId()))
                                if (ae.isCorrectAnswer() && ad.getCorrectAnswer()) {
                                    grade++;
                                }
                        }
                }
            }
        }
        short percentage = (short) ((grade * 100) / maxGrade);
        TestResultsEntity testResultsEntity = testResultsService.saveResults(userPrinciple.getId(),
                new Timestamp(System.currentTimeMillis()), percentage, testDto.getId());
        return new ResponseEntity<>(testResultsEntity.getResult(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/deleteTest")
    public ResponseEntity<Void> deleteTopic(@RequestBody Long courseId) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Courses course = coursesService.getCourseById(courseId);
        if (course.getProfessorId().equals(userPrinciple.getId())) {
            testService.deleteTestById(courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
