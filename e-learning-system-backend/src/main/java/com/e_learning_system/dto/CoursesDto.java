package com.e_learning_system.dto;


import com.e_learning_system.entities.TestsEntity;
import com.e_learning_system.entities.TopicsEntity;

import java.sql.Timestamp;
import java.util.Set;

public class CoursesDto {


    private Long id;
    private String title;
    private String description;
    private Long professorId;
    private boolean isJoin;
    private boolean isAuthor;
    private boolean isStarted;
    private Timestamp startDate;
    private Timestamp endDate;
    private TestsEntity test;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }

    public boolean isAuthor() {
        return isAuthor;
    }

    public void setAuthor(boolean author) {
        isAuthor = author;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public TestsEntity getTest() {
        return test;
    }

    public void setTest(TestsEntity testsEntity) {
        this.test = testsEntity;
    }


//    public Set<CourseResources> getResources() {
//        return resources;
//    }
//
//    public void setResources(Set<CourseResources> resources) {
//        this.resources = resources;
//    }


}
