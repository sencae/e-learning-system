package com.e_learning_system.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "answers", schema = "public")
public class AnswersEntity {
    private Long id;
    private String answer;
    private Long parentQuestion;
    private boolean correctAnswer;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seqAns", sequenceName = "answers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAns")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return correctAnswer == that.correctAnswer &&
                Objects.equals(id, that.id) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(parentQuestion, that.parentQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer, parentQuestion, correctAnswer);
    }
}
