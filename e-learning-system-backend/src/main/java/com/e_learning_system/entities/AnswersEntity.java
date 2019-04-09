package com.e_learning_system.entities;

import javax.persistence.*;

@Entity
@Table(name = "answers", schema = "public")
public class AnswersEntity {
    private long id;
    private String answer;
    private Long parentQuestion;
    private boolean correctAnswer;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "answer", nullable = false, length = 255)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "parent_question", nullable = true)
    public Long getParentQuestion() {
        return parentQuestion;
    }

    public void setParentQuestion(Long parentQuestion) {
        this.parentQuestion = parentQuestion;
    }

    @Basic
    @Column(name = "correct_answer", nullable = false)
    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswersEntity that = (AnswersEntity) o;

        if (id != that.id) return false;
        if (correctAnswer != that.correctAnswer) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (parentQuestion != null ? !parentQuestion.equals(that.parentQuestion) : that.parentQuestion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (parentQuestion != null ? parentQuestion.hashCode() : 0);
        result = 31 * result + (correctAnswer ? 1 : 0);
        return result;
    }
}
