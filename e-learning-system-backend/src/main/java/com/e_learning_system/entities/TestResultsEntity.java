package com.e_learning_system.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "test_results", schema = "public")
public class TestResultsEntity {
    private Long id;
    private Long userId;
    private Timestamp date;
    private Short result;
    private Long testId;

    @Id
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

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;
        if (testId != null ? !testId.equals(that.testId) : that.testId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = (int) (id ^ (id >>> 32));
        result1 = 31 * result1 + (userId != null ? userId.hashCode() : 0);
        result1 = 31 * result1 + (date != null ? date.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (testId != null ? testId.hashCode() : 0);
        return result1;
    }
}
