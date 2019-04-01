package com.e_learning_system.dto;


import com.e_learning_system.entities.CourseResources;
import com.e_learning_system.entities.TopicsEntity;

import java.util.Set;

public class CoursesDto {


    private Long id;
    private String title;
    private String description;
    private Long professorId;
    //private Set<CourseResources> resources;
    private Set<TopicsEntity> topics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Set<TopicsEntity> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicsEntity> topics) {
        this.topics = topics;
    }

//    public Set<CourseResources> getResources() {
//        return resources;
//    }
//
//    public void setResources(Set<CourseResources> resources) {
//        this.resources = resources;
//    }


}
