package com.e_learning_system.dto;

public class AnswerDto {
    private Long id;
    private String answer;
    private Boolean correctAnswer;
    private Long parentQuestion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Long getParentQuestion() {
        return parentQuestion;
    }

    public void setParentQuestion(Long parentQuestion) {
        this.parentQuestion = parentQuestion;
    }
}
