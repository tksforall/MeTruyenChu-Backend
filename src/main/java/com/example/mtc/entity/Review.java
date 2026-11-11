package com.example.mtc.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "REVIEWS")
public class Review {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "review_seq"
    )
    @SequenceGenerator(
            name = "review_seq",
            sequenceName = "REVIEW_SEQ",
            allocationSize = 1
    )
    @Column(name = "REVIEW_ID")
    private Long reviewId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;

    @Column(name = "PARENT_REVIEW_ID")
    private Long parentReviewId;

    @Column(name = "CHAPTER_ID")
    private Long chapterId;

    @Column(name = "RATING")
    private Double rating;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "LIKE_COUNT", nullable = false)
    private Long likeCount = 0L;

    @Column(name = "REPLY_COUNT", nullable = false)
    private Long replyCount = 0L;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    public Review() {
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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getParentReviewId() {
        return parentReviewId;
    }

    public void setParentReviewId(Long parentReviewId) {
        this.parentReviewId = parentReviewId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}