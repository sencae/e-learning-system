package com.e_learning_system.dto;

import com.e_learning_system.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil extends ModelMapper {
    public ModelMapperUtil() {
        this.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        this.addMappings(new PropertyMap<User, UserDto>() {
            @Override
            protected void configure() {
                map().setUniversity(source.getUserInfo().getUniversity());
                map().setUrl(source.getUserInfo().getAvatarUrl());
                map().setBriefInformation(source.getUserInfo().getBriefInformation());
            }
        });

    }
}
