package com.example.mtc.repository;

import com.example.mtc.entity.ReviewLike;
import com.example.mtc.entity.ReviewLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewLikeRepository
        extends JpaRepository<
        ReviewLike,
        ReviewLikeId> {

    List<ReviewLike> findByReviewId(
            Long reviewId
    );

    boolean existsByReviewIdAndUserId(
            Long reviewId,
            Long userId
    );

    void deleteByReviewIdAndUserId(
            Long reviewId,
            Long userId
    );

    void deleteByReviewId(
            Long reviewId
    );
}