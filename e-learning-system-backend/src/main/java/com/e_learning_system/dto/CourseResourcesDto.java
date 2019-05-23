package com.e_learning_system.dto;

import com.e_learning_system.entities.CourseResources;

import java.util.List;

public class CourseResourcesDto {
    private Long topicId;
    private List<CourseResources> links;

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public List<CourseResources> getLinks() {
        return links;
    }

    public void setLinks(List<CourseResources> links) {
        this.links = links;
    }
}
