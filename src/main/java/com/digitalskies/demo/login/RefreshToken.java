package com.digitalskies.demo.login;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "refresh_token")
public class RefreshToken {

    @Id
    private String userId;

    private String hashedToken;

    private long createdAt;

    private long expiresAt;

    public RefreshToken(){

    }


    public RefreshToken(String userId, String hashedToken,long createdAt, long expiresAt) {
        this.userId=userId;
        this.createdAt=createdAt;
        this.expiresAt = expiresAt;
        this.hashedToken = hashedToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHashedToken() {
        return hashedToken;
    }

    public void setHashedToken(String hashedToken) {
        this.hashedToken = hashedToken;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }
}
