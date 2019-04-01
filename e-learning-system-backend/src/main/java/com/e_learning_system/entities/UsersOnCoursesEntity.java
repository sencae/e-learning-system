package com.e_learning_system.entities;

import javax.persistence.*;

@Entity
@Table(name = "users_on_courses", schema = "public")
public class UsersOnCoursesEntity {
    private Long id;
    private Long userId;
    private Long courseId;

    public UsersOnCoursesEntity(){}

public UsersOnCoursesEntity(Long userId,Long courseId){
    this.userId = userId;
    this.courseId = courseId;
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

        UsersOnCoursesEntity that = (UsersOnCoursesEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        return result;
    }
}
