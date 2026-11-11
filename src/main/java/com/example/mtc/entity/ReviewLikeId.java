package com.example.mtc.entity;

import java.io.Serializable;
import java.util.Objects;

public class ReviewLikeId implements Serializable {

    private Long reviewId;
    private Long userId;

    public ReviewLikeId() {
    }

    public ReviewLikeId(
            Long reviewId,
            Long userId
    ) {
        this.reviewId = reviewId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof ReviewLikeId)) return false;

        ReviewLikeId that =
                (ReviewLikeId) o;

        return Objects.equals(
                reviewId,
                that.reviewId
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
                reviewId,
                userId
        );
    }
}