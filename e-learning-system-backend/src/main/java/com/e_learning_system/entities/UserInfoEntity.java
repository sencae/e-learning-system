package com.e_learning_system.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_info", schema = "public")
public class UserInfoEntity {
    private long id;
    private String avatarUrl;
    private String university;
    private String briefInformation;

    @Id
    @Column(name = "user_id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "avatar_url", nullable = true, length = 255)
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Basic
    @Column(name = "university", nullable = true, length = 255)
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Basic
    @Column(name = "brief_information", nullable = true, length = 255)
    public String getBriefInformation() {
        return briefInformation;
    }

    public void setBriefInformation(String briefInformation) {
        this.briefInformation = briefInformation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfoEntity that = (UserInfoEntity) o;

        if (id != that.id) return false;
        if (avatarUrl != null ? !avatarUrl.equals(that.avatarUrl) : that.avatarUrl != null) return false;
        if (university != null ? !university.equals(that.university) : that.university != null) return false;
        if (briefInformation != null ? !briefInformation.equals(that.briefInformation) : that.briefInformation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (university != null ? university.hashCode() : 0);
        result = 31 * result + (briefInformation != null ? briefInformation.hashCode() : 0);

        return result;
    }


}
