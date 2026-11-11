package com.example.mtc.entity;

import java.io.Serializable;
import java.util.Objects;

public class CommentLikeId implements Serializable {

    private Long commentId;

    private Long userId;


    public CommentLikeId() {
    }


    public CommentLikeId(
            Long commentId,
            Long userId
    ) {

        this.commentId = commentId;
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof CommentLikeId)) {
            return false;
        }

        CommentLikeId that =
                (CommentLikeId) o;

        return Objects.equals(
                commentId,
                that.commentId
        )
                &&
                Objects.equals(
                        userId,
                        that.userId
                );
    }


    @Override
    public int hashCode() {

        return Objects.hash(
                commentId,
                userId
        );
    }
}