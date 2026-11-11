package com.example.mtc.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENT_LIKES")
@IdClass(CommentLikeId.class)
public class CommentLike {

    @Id
    @Column(name = "COMMENT_ID")
    private Long commentId;

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;


    public CommentLike() {
    }


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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

    public void setCreatedAt(
            LocalDateTime createdAt
    ) {

        this.createdAt = createdAt;
    }
}