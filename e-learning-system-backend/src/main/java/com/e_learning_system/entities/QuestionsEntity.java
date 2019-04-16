package com.e_learning_system.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "questions", schema = "public")
public class QuestionsEntity {
    private Long id;
    private String question;
    private Long parentTest;
    private Boolean checkboxType;
    private Set<AnswersEntity> answers;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seqQue", sequenceName = "questions_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqQue")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_question")
    public Set<AnswersEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswersEntity> answersEntities) {
        this.answers = answersEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionsEntity that = (QuestionsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(question, that.question) &&
                Objects.equals(parentTest, that.parentTest) &&
                Objects.equals(checkboxType, that.checkboxType) &&
                Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, parentTest, checkboxType, answers);
    }
}
