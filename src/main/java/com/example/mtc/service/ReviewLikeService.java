package com.example.mtc.service;

import com.example.mtc.entity.Review;
import com.example.mtc.entity.ReviewLike;
import com.example.mtc.repository.ReviewLikeRepository;
import com.example.mtc.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewLikeService {

    private final ReviewLikeRepository repository;
    private final ReviewRepository reviewRepository;

    public ReviewLikeService(
            ReviewLikeRepository repository,
            ReviewRepository reviewRepository
    ) {

        this.repository = repository;
        this.reviewRepository = reviewRepository;
    }


    // ======================================================
    // LẤY TẤT CẢ LIKE CỦA REVIEW
    // ======================================================

    public List<ReviewLike> getByReviewId(
            Long reviewId
    ) {

        return repository.findByReviewId(
                reviewId
        );
    }


    // ======================================================
    // LIKE REVIEW
    // ======================================================

    @Transactional
    public ReviewLike like(
            Long reviewId,
            Long userId
    ) {

        // Kiểm tra review có tồn tại không
        Review review =
                reviewRepository.findById(
                        reviewId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy review"
                        )
                );


        // Kiểm tra user đã like chưa
        if (
                repository.existsByReviewIdAndUserId(
                        reviewId,
                        userId
                )
        ) {

            throw new RuntimeException(
                    "User đã like review này"
            );
        }


        // Tạo like
        ReviewLike like =
                new ReviewLike();

        like.setReviewId(reviewId);

        like.setUserId(userId);

        like.setCreatedAt(
                LocalDateTime.now()
        );


        ReviewLike savedLike =
                repository.save(like);


        // Tăng LIKE_COUNT
        if (review.getLikeCount() == null) {

            review.setLikeCount(0L);
        }

        review.setLikeCount(
                review.getLikeCount() + 1
        );

        reviewRepository.save(review);


        return savedLike;
    }


    // ======================================================
    // UNLIKE REVIEW
    // ======================================================

    @Transactional
    public void unlike(
            Long reviewId,
            Long userId
    ) {

        // Kiểm tra review có tồn tại không
        Review review =
                reviewRepository.findById(
                        reviewId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy review"
                        )
                );


        // Kiểm tra user có like không
        boolean exists =
                repository.existsByReviewIdAndUserId(
                        reviewId,
                        userId
                );


        if (!exists) {

            throw new RuntimeException(
                    "User chưa like review này"
            );
        }


        // Xóa like
        repository.deleteByReviewIdAndUserId(
                reviewId,
                userId
        );


        // Giảm LIKE_COUNT
        if (
                review.getLikeCount() != null
                        && review.getLikeCount() > 0
        ) {

            review.setLikeCount(
                    review.getLikeCount() - 1
            );

            reviewRepository.save(review);
        }
    }

    // ======================================================
    // KIỂM TRA USER ĐÃ LIKE REVIEW CHƯA
    // ======================================================

    public boolean isLiked(
            Long reviewId,
            Long userId
    ) {
        return repository.existsByReviewIdAndUserId(
                reviewId,
                userId
        );
    }
}