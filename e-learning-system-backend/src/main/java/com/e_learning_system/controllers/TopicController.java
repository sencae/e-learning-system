package com.e_learning_system.controllers;

import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.dto.TopicDto;
import com.e_learning_system.entities.CourseResources;
import com.e_learning_system.entities.TopicsEntity;
import com.e_learning_system.googleApi.GoogleDriveService;
import com.e_learning_system.services.TopicsService;
import com.e_learning_system.services.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController extends BaseGetController {
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);
    private final ModelMapperUtil modelMapperUtil;
    private final TopicsService topicsService;
    private final GoogleDriveService googleDriveService;
    private final UtilService utilService;

    @Autowired
    public TopicController(ModelMapperUtil modelMapperUtil, TopicsService topicsService, GoogleDriveService googleDriveService, UtilService utilService) {
        this.modelMapperUtil = modelMapperUtil;
        this.topicsService = topicsService;
        this.googleDriveService = googleDriveService;
        this.utilService = utilService;
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/createTopic")
    public ResponseEntity<TopicsEntity> createTopic(@RequestBody TopicDto topicDto) {
        if (topicDto.getCourseId() < 0)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        TopicsEntity topicsEntity = modelMapperUtil.map(topicDto, TopicsEntity.class);
        topicsEntity = topicsService.createTopic(topicsEntity);
        return new ResponseEntity<>(topicsEntity, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/deleteTopic")
    public ResponseEntity<Void> deleteTopic(@RequestBody TopicsEntity topic) {
        int counter = 0;
        for (CourseResources courseResources : topic.getCourseResources()) {
            if (
                    googleDriveService.deleteFile(
                            googleDriveService.getDriveService(),
                            utilService.getFileIdFromUrl(
                                    courseResources.getUrl()
                            )
                    )
            )
                counter++;
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (counter == topic.getCourseResources().size()) {
            try {
                topicsService.deleteTopic(topic);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception ex) {
                logger.error("Error. Message - {}", ex.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
