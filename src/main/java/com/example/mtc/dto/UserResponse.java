package com.example.mtc.dto;

public class UserResponse {

    private Long userId;
    private String userName;
    private String email;
    private String avatarUrl;
    private Long recommendationCount;
    private String status;
    private String authProvider;
    private Integer emailVerified;
    private String phone;
    private String gender;


    public UserResponse() {
    }

    public UserResponse(
            Long userId,
            String userName,
            String email,
            String avatarUrl,
            Long recommendationCount,
            String status,
            String authProvider,
            Integer emailVerified,
            String phone,
            String gender
    ) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.recommendationCount = recommendationCount;
        this.status = status;
        this.authProvider = authProvider;
        this.emailVerified = emailVerified;
        this.phone = phone;
        this.gender = gender;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getRecommendationCount() {
        return recommendationCount;
    }

    public void setRecommendationCount(Long recommendationCount) {
        this.recommendationCount = recommendationCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public Integer getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Integer emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}