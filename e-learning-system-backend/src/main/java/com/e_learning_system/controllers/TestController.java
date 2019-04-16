package com.e_learning_system.controllers;

import com.e_learning_system.dto.*;
import com.e_learning_system.entities.AnswersEntity;
import com.e_learning_system.entities.QuestionsEntity;
import com.e_learning_system.entities.TestResultsEntity;
import com.e_learning_system.entities.TestsEntity;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.CoursesService;
import com.e_learning_system.services.QuestionService;
import com.e_learning_system.services.TestResultsService;
import com.e_learning_system.services.TestService;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

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
    public ResponseEntity<String> createTest(@RequestBody CreateTestDto createTestDto) {
        if (createTestDto.getCourseId() < 0)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        if (coursesService.getCourseById(createTestDto.getCourseId()).getTestsEntity() != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        TestsEntity testsEntity = modelMapperUtil.map(createTestDto, TestsEntity.class);
        testsEntity = testService.createTest(testsEntity);
        return new ResponseEntity<>(testsEntity.getTestName(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/getTestEdit")
    public ResponseEntity<TestDto> getTestEdit(@RequestBody Long courseId) {
        if (courseId < 0)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        TestDto testDto = modelMapperUtil.map(coursesService.getCourseById(courseId).getTestsEntity(), TestDto.class);
        return new ResponseEntity<>(testDto, HttpStatus.OK);
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
    public ResponseEntity<Void> saveTest(@RequestBody TestDto testDto) {
        Type listType = new TypeToken<List<QuestionsEntity>>() {
        }.getType();
        List<QuestionsEntity> questionsEntity = modelMapperUtil.map(testDto.getQuestions(), listType);
        for (QuestionsEntity qe : questionsEntity)
            qe.setParentTest(testDto.getId());
        questionsEntity = questionService.saveQuestions(questionsEntity);
        for (QuestionsEntity qe : questionsEntity)
            for (QuestionDto qd : testDto.getQuestions()) {
                if (qe.getQuestion().equals(qd.getQuestion())) {
                    qd.getAnswers().forEach(answerDto -> answerDto.setParentQuestion(qe.getId()));
                    Type setType = new TypeToken<Set<AnswersEntity>>() {
                    }.getType();
                    qe.setAnswers(modelMapperUtil.map(qd.getAnswers(), setType));
                }

            }
        questionService.saveQuestions(questionsEntity);
        return new ResponseEntity<>(HttpStatus.OK);
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
}
