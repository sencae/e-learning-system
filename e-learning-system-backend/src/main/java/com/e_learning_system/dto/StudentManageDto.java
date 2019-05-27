package com.e_learning_system.dto;

import com.e_learning_system.entities.TestResultsEntity;

public class StudentManageDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String url;
    private TestResultsEntity testResults;
    private String testName;
    private String taskUrl;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TestResultsEntity getTestResults() {
        return testResults;
    }

    public void setTestResults(TestResultsEntity testResults) {
        this.testResults = testResults;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }
}
