package com.plucas.graphql.datasource.problems.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users_token")
public class UserToken {

    @Id
    private UUID userId;
    private String authToken;
    @CreationTimestamp
    private LocalDateTime createTimestamp;
    private LocalDateTime expiryTimestamp;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime creationTimestamp) {
        this.createTimestamp = creationTimestamp;
    }

    public LocalDateTime getExpiryTimestamp() {
        return expiryTimestamp;
    }

    public void setExpiryTimestamp(LocalDateTime expiryTimestamp) {
        this.expiryTimestamp = expiryTimestamp;
    }
}
