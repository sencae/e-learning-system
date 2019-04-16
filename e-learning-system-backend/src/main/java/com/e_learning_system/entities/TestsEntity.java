package com.e_learning_system.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tests", schema = "public")
public class TestsEntity {
    private Long id;
    private String testName;
    private Set<QuestionsEntity> questions;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "test_name")
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "parent_test")
    public Set<QuestionsEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionsEntity> questionsEntities) {
        this.questions = questionsEntities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestsEntity that = (TestsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(testName, that.testName) &&
                Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testName, questions);
    }
}
