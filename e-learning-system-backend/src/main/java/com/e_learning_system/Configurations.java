package com.e_learning_system;

import com.e_learning_system.dto.ModelMapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {
    @Bean
    public ModelMapperUtil modelMapper() {
        return new ModelMapperUtil();
    }
}
