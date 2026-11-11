package com.example.mtc.repository;

import com.example.mtc.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository
        extends JpaRepository<Review, Long> {

    List<Review> findByBookId(Long bookId);

    List<Review> findByUserId(Long userId);

    List<Review> findByBookIdAndParentReviewIdIsNull(
            Long bookId
    );

    List<Review> findByParentReviewId(
            Long parentReviewId
    );

    List<Review> findByChapterId(
            Long chapterId
    );
}