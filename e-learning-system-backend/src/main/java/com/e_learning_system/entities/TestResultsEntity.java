package com.e_learning_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "test_results", schema = "public")
public class TestResultsEntity {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long userId;
    private Timestamp date;
    private Short result;
    @JsonIgnore
    private Long testId;
    @JsonIgnore
    private TestsEntity testsEntity;


    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seqTesR", sequenceName = "test_results_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTesR")
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
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "result", nullable = true)
    public Short getResult() {
        return result;
    }

    public void setResult(Short result) {
        this.result = result;
    }

    @Basic
    @Column(name = "test_id", nullable = true)
    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestResultsEntity that = (TestResultsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(result, that.result) &&
                Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, date, result, testId);
    }

    @ManyToOne
    @JoinColumn(name = "test_id", insertable = false, updatable = false)
    public TestsEntity getTestsEntity() {
        return testsEntity;
    }

    public void setTestsEntity(TestsEntity testsEntity) {
        this.testsEntity = testsEntity;
    }
}
