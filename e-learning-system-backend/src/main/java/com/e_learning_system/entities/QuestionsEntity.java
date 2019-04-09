package com.e_learning_system.entities;

import javax.persistence.*;

@Entity
@Table(name = "questions", schema = "public")
public class QuestionsEntity {
    private long id;
    private String question;
    private Long parentTest;
    private Boolean checkboxType;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "question", nullable = false, length = 255)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "parent_test", nullable = true)
    public Long getParentTest() {
        return parentTest;
    }

    public void setParentTest(Long parentTest) {
        this.parentTest = parentTest;
    }

    @Basic
    @Column(name = "checkbox_type", nullable = true)
    public Boolean getCheckboxType() {
        return checkboxType;
    }

    public void setCheckboxType(Boolean checkboxType) {
        this.checkboxType = checkboxType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionsEntity that = (QuestionsEntity) o;

        if (id != that.id) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (parentTest != null ? !parentTest.equals(that.parentTest) : that.parentTest != null) return false;
        if (checkboxType != null ? !checkboxType.equals(that.checkboxType) : that.checkboxType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (parentTest != null ? parentTest.hashCode() : 0);
        result = 31 * result + (checkboxType != null ? checkboxType.hashCode() : 0);
        return result;
    }
}
