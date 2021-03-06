package com.e_learning_system.entities;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courses", schema = "public")
public class Courses {


    private Long id;
    private String title;
    private String description;
    private Long professorId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String url;
    private CourseFile courseFile;
    private Integer endType;
    private List<TopicsEntity> topicsEntities;
    @OneToOne
    private TestsEntity testsEntity;
    private Set<User> usersOnCourse;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    public TestsEntity getTestsEntity() {
        return testsEntity;
    }

    public void setTestsEntity(TestsEntity testsEntity) {
        this.testsEntity = testsEntity;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinTable(name = "users_on_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<User> getUsersOnCourse() {
        return usersOnCourse;
    }

    public void setUsersOnCourse(Set<User> usersOnCourse) {
        this.usersOnCourse = usersOnCourse;
    }




    @OneToMany
    @JoinColumn(name = "course_id")
    public List<TopicsEntity> getTopicsEntities() {
        return topicsEntities;
    }

    public void setTopicsEntities(List<TopicsEntity> topicsEntities) {
        this.topicsEntities = topicsEntities;
    }


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
    @Column(name = "end_type", nullable = false)
    public Integer getEndType() {
        return endType;
    }

    public void setEndType(Integer endType) {
        this.endType = endType;
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
    @Column(name = "professor_id", nullable = false)
    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = false)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "img_url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courses courses = (Courses) o;
        return Objects.equals(id, courses.id) &&
                Objects.equals(title, courses.title) &&
                Objects.equals(description, courses.description) &&
                Objects.equals(professorId, courses.professorId) &&
                Objects.equals(startDate, courses.startDate) &&
                Objects.equals(endDate, courses.endDate) &&
                Objects.equals(topicsEntities, courses.topicsEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, professorId, startDate, endDate, topicsEntities);
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    public CourseFile getCourseFile() {
        return courseFile;
    }

    public void setCourseFile(CourseFile courseFile) {
        this.courseFile = courseFile;
    }
}
