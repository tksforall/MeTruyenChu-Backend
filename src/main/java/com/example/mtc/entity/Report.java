package com.example.mtc.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "REPORTS")
public class Report {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "report_seq"
    )
    @SequenceGenerator(
            name = "report_seq",
            sequenceName = "REPORTS_SEQ",
            allocationSize = 1
    )
    @Column(name = "REPORT_ID")
    private Long reportId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "COMMENT_ID")
    private Long commentId;

    @Column(name = "REVIEW_ID")
    private Long reviewId;

    @Column(name = "CHAPTER_ID")
    private Long chapterId;

    @Column(name = "REASON", nullable = false, length = 2000)
    private String reason;

    @Column(name = "STATUS", nullable = false, length = 30)
    private String status;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;


    public Report() {
    }


    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }


    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }


    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}