package com.example.mtc.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "REVIEW_LIKES")
@IdClass(ReviewLikeId.class)
public class ReviewLike {

    @Id
    @Column(name = "REVIEW_ID")
    private Long reviewId;

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    public ReviewLike() {
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}