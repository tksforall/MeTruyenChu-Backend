package com.example.mtc.dto;

import java.time.LocalDateTime;

public class ReviewResponse {

    private Long reviewId;
    private Long userId;
    private String userName;
    private Long bookId;
    private Long parentReviewId;
    private Long chapterId;
    private Double rating;
    private String content;
    private Long likeCount;
    private Long replyCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReviewResponse() {
    }

    public ReviewResponse(
            Long reviewId,
            Long userId,
            String userName,
            Long bookId,
            Long parentReviewId,
            Long chapterId,
            Double rating,
            String content,
            Long likeCount,
            Long replyCount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.userName = userName;
        this.bookId = bookId;
        this.parentReviewId = parentReviewId;
        this.chapterId = chapterId;
        this.rating = rating;
        this.content = content;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getParentReviewId() {
        return parentReviewId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public Double getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public Long getReplyCount() {
        return replyCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}