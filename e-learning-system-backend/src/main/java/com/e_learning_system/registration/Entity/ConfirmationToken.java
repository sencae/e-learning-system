package com.e_learning_system.registration.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmation_token", schema = "public")
public class ConfirmationToken {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "confirmation_token_token_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "token_id", nullable = false)
    private long token_id;

    @Column(name = "confirmation_token")
    private String confirmation_Token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    public ConfirmationToken(User user) {
        this.user = user;
        createdDate = new Date();
        confirmation_Token = UUID.randomUUID().toString();
    }

    public ConfirmationToken() {
    }

    public long getTokenid() {
        return token_id;
    }

    public void setTokenid(long tokenid) {
        this.token_id = tokenid;
    }

    public String getConfirmationToken() {
        return confirmation_Token;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmation_Token = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
