package com.e_learning_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "courses", schema = "public")
public class Courses {

    @JsonIgnore
    private Long id;

    private String title;
    private String description;
    private Long professorId;

    //    @ManyToMany(fetch = FetchType.LAZY, targetEntity = CourseResources.class)
//    private Set<CourseResources> resources = new HashSet<>();
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    private Set<CourseType> types = new HashSet<>();
    public Courses() {
    }

    public Courses(String title, String description, Long professorId) {
        this.title = title;
        this.description = description;
        this.professorId = professorId;
    }

    @JsonIgnore
    @OneToOne
    private User userById;

    @OneToOne
    @JoinColumn(name = "professorId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public User getUserById() {
        return userById;
    }

    public void setUserById(User userById) {
        this.userById = userById;
    }


//    @ManyToMany(fetch = FetchType.LAZY, targetEntity = CourseResources.class)
//    @JoinTable(name = "resources_of_course",
//            joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "resource_id"))
//    public Set<CourseResources> getResources() {
//        return resources;
//    }
//
//    public void setResources(Set<CourseResources> resources) {
//        this.resources = resources;
//    }
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "type_of_courses",
//               joinColumns = @JoinColumn(name = "course_id"),
//               inverseJoinColumns = @JoinColumn(name = "type_id"))
//    public Set<CourseType> getTypes() {
//        return types;
//    }
//
//    public void setTypes(Set<CourseType> types) {
//        this.types = types;
//    }


    @Id
    @SequenceGenerator(name = "courses_id_seq", sequenceName = "courses_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courses_id_seq")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "professorId", nullable = false)
    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courses courses = (Courses) o;
        return Objects.equals(id, courses.id) &&
                Objects.equals(title, courses.title) &&
                Objects.equals(description, courses.description) &&
                Objects.equals(professorId, courses.professorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, professorId);
    }
}
