package com.e_learning_system.dto;

import org.springframework.web.multipart.MultipartFile;

public class UpdateUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String university;
    private String briefInformation;
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBriefInformation() {
        return briefInformation;
    }

    public void setBriefInformation(String briefInformation) {
        this.briefInformation = briefInformation;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
