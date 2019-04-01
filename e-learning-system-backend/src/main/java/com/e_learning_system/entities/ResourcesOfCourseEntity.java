package com.e_learning_system.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resources_of_course", schema = "public")
public class ResourcesOfCourseEntity {
    private Long resourceId;
    private Long courseId;
    private Long id;

    @Basic
    @Column(name = "resource_id", nullable = true)
    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "course_id", nullable = true)
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Id
    @SequenceGenerator(name = "seqCou", sequenceName = "resources_of_course_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCou")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourcesOfCourseEntity that = (ResourcesOfCourseEntity) o;

        if (id != that.id) return false;
        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;

        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(resourceId, courseId, id);
    }
}
