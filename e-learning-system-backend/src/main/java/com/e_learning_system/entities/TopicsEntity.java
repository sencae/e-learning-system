package com.e_learning_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topics", schema = "public")
public class TopicsEntity {
    private Long id;
    private String title;
    private Long courseId;
    private List<CourseResources> courseResources;
    @JsonIgnore
    private Courses course;

    @OneToMany
    @JoinColumn(name = "topic_id", updatable = false)
    public List<CourseResources> getCourseResources() {
        return courseResources;
    }

    public void setCourseResources(List<CourseResources> courseResources) {
        this.courseResources = courseResources;
    }

    @Id
    @SequenceGenerator(name = "seqTop", sequenceName = "topics_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTop")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "course_id", nullable = true)
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicsEntity that = (TopicsEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }
}
