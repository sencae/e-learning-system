package com.e_learning_system.controllers;

import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.dto.TopicDto;
import com.e_learning_system.entities.TopicsEntity;
import com.e_learning_system.services.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController extends BaseGetController {

    private final ModelMapperUtil modelMapperUtil;
    private final TopicsService topicsService;

    @Autowired
    public TopicController(ModelMapperUtil modelMapperUtil, TopicsService topicsService) {
        this.modelMapperUtil = modelMapperUtil;
        this.topicsService = topicsService;
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/createTopic")
    public ResponseEntity<TopicsEntity> createCourse(@RequestBody TopicDto topicDto) {
        TopicsEntity topicsEntity = modelMapperUtil.map(topicDto, TopicsEntity.class);
        topicsEntity = topicsService.createTopic(topicsEntity);
        return new ResponseEntity<>(topicsEntity,HttpStatus.OK);
    }
}
