package com.e_learning_system.entities;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "users_on_courses", schema = "public")
public class UsersOnCoursesEntity {
    private Long id;
    private Long userId;
    private Long courseId;
    private Boolean finished;
    private String checkUrl;

    public UsersOnCoursesEntity(){}

public UsersOnCoursesEntity(Long userId,Long courseId){
    this.userId = userId;
    this.courseId = courseId;
    this.finished = false;
}
    @Id
    @SequenceGenerator(name = "seqUOC", sequenceName = "users_on_courses_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUOC")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "finished", nullable = false)
    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Basic
    @Column(name = "course_id", nullable = true)
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }


    @Basic
    @Column(name = "check_url")
    public String getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersOnCoursesEntity that = (UsersOnCoursesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(finished, that.finished) &&
                Objects.equals(checkUrl, that.checkUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, courseId, finished, checkUrl);
    }
}
