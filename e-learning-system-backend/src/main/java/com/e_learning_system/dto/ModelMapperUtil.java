package com.e_learning_system.dto;

import com.e_learning_system.entities.AnswersEntity;
import com.e_learning_system.entities.Courses;
import com.e_learning_system.entities.TopicsEntity;
import com.e_learning_system.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil extends ModelMapper {
    public ModelMapperUtil() {
        this.getConfiguration().setFieldMatchingEnabled(true);
        this.addMappings(new PropertyMap<User, UserDto>() {
            @Override
            protected void configure() {
                map().setUniversity(source.getUserInfo().getUniversity());
                map().setUrl(source.getUserInfo().getAvatarUrl());
                map().setBriefInformation(source.getUserInfo().getBriefInformation());
            }
        });
        this.addMappings(new PropertyMap<UpdateUserDto, User>() {
            @Override
            protected void configure() {
                map().getUserInfo().setUniversity(source.getUniversity());
                map().getUserInfo().setBriefInformation(source.getBriefInformation());
                map().getUserInfo().setAvatarUrl(source.getUrl());
            }
        });
        this.addMappings(new PropertyMap<TopicDto, TopicsEntity>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        });
        this.addMappings(new PropertyMap<Courses, CoursesDto>() {
            @Override
            protected void configure() {
                map().setTest(source.getTestsEntity().getTestName());
            }
        });
        this.addMappings(new PropertyMap<AnswerDto, AnswersEntity>() {
            @Override
            protected void configure() {
                map().setCorrectAnswer(source.getCorrectAnswer());
                map().setAnswer(source.getAnswer());
                map().setParentQuestion(source.getParentQuestion());
            }
        });
        this.addMappings(new PropertyMap<User, StudentManageDto>() {
            @Override
            protected void configure() {
                map().setUserId(source.getId());
                map().setUrl(source.getUserInfo().getAvatarUrl());
            }
        });


    }
}
