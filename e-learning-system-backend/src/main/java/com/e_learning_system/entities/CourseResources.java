package com.e_learning_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course_resources", schema = "public")
public class CourseResources {
    @JsonIgnore
    private Long id;

    private String url;
    private String title;

    private Long topicId;
    @Id
    @SequenceGenerator(name = "seqRes", sequenceName = "course_resources_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRes")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Basic
    @Column(name = "topic_id",nullable = false)
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseResources that = (CourseResources) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(url, that.url) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, title);
    }
}
