package com.example.mtc.service;

import com.example.mtc.dto.ReviewResponse;
import com.example.mtc.entity.Review;
import com.example.mtc.entity.User;
import com.example.mtc.repository.ReviewRepository;
import com.example.mtc.repository.ReviewLikeRepository;
import com.example.mtc.repository.ReportRepository;

import com.example.mtc.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;


    public ReviewService(
            ReviewRepository repository,
            ReviewLikeRepository reviewLikeRepository,
            ReportRepository reportRepository,
            UserRepository userRepository
    ) {

        this.repository = repository;
        this.reviewLikeRepository = reviewLikeRepository;
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    private Review attachUserName(Review review) {
        if (review != null && review.getUserId() != null) {
            String name = userRepository.findById(review.getUserId())
                    .map(User::getUserName) // hoặc User::getUsername tùy Entity User.java của bạn
                    .orElse("User #" + review.getUserId());
            review.setUserName(name);
        }
        return review;
    }

    // ======================================================
    // LẤY REVIEW THEO ID
    // ======================================================

    public Review getReviewById(
            Long reviewId
    ) {
        Review review = repository
                .findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy review với ID: "
                                        + reviewId
                        )
                );
        return attachUserName(review);
    }


    // ======================================================
    // LẤY TẤT CẢ REVIEW CỦA TRUYỆN
    // ======================================================

    public List<Review> getByBookId(Long bookId) {

        return repository.findByBookId(bookId);
    }


    // ======================================================
    // CHỈ LẤY REVIEW CHÍNH
    // ======================================================

    public List<ReviewResponse> getMainReviews(Long bookId) {

        List<Review> reviews =
                repository.findByBookIdAndParentReviewIdIsNull(bookId);

        return reviews.stream()
                .map(review -> {

                    String userName =
                            userRepository.findById(review.getUserId())
                                    .map(user -> user.getUserName())
                                    .orElse("Unknown User");

                    return new ReviewResponse(
                            review.getReviewId(),
                            review.getUserId(),
                            userName,
                            review.getBookId(),
                            review.getParentReviewId(),
                            review.getChapterId(),
                            review.getRating(),
                            review.getContent(),
                            review.getLikeCount(),
                            review.getReplyCount(),
                            review.getCreatedAt(),
                            review.getUpdatedAt()
                    );
                })
                .toList();
    }


    // ======================================================
    // LẤY REPLY
    // ======================================================

    public List<Review> getReplies(Long reviewId) {
        List<Review> replies = repository.findByParentReviewId(reviewId);
        replies.forEach(this::attachUserName);
        return replies;
    }


    // ======================================================
    // LẤY REVIEW CỦA USER
    // ======================================================

    public List<Review> getByUserId(Long userId) {

        return repository.findByUserId(userId);
    }


    // ======================================================
    // THÊM REVIEW
    // ======================================================

    public Review createReview(
            Review review,
            Long userId
    ) {

        // Không cho client tự quyết định user
        review.setUserId(userId);

        review.setCreatedAt(
                LocalDateTime.now()
        );

        review.setUpdatedAt(
                LocalDateTime.now()
        );

        if (review.getLikeCount() == null) {
            review.setLikeCount(0L);
        }

        if (review.getReplyCount() == null) {
            review.setReplyCount(0L);
        }

        // ======================================================
        // NẾU LÀ REPLY -> TĂNG REPLY_COUNT CỦA REVIEW CHA
        // ======================================================

        if (review.getParentReviewId() != null) {

            Review parentReview =
                    repository.findById(
                            review.getParentReviewId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Không tìm thấy review cha"
                            )
                    );

            if (parentReview.getReplyCount() == null) {
                parentReview.setReplyCount(0L);
            }

            parentReview.setReplyCount(
                    parentReview.getReplyCount() + 1
            );

            repository.save(parentReview);
        }

        // ======================================================
        // LƯU REVIEW / REPLY
        // ======================================================

        return repository.save(review);
    }


    // ======================================================
    // SỬA REVIEW
    // ======================================================

    public Review updateReview(
            Long reviewId,
            String content,
            Double rating,
            Long userId
    ) {

        Review review = repository
                .findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy review"
                        )
                );

        if (!review.getUserId().equals(userId)) {

            throw new RuntimeException(
                    "Bạn không có quyền sửa review này"
            );
        }

        review.setContent(content);

        if (rating != null) {
            review.setRating(rating);
        }

        review.setUpdatedAt(
                LocalDateTime.now()
        );

        return repository.save(review);
    }


    // ======================================================
    // XÓA REVIEW + REPLY + LIKE + REPORT
    // ======================================================

    @Transactional
    public void deleteReview(
            Long reviewId,
            Long userId
    ) {

        Review review = repository
                .findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy review"
                        )
                );

        if (!review.getUserId().equals(userId)) {

            throw new RuntimeException(
                    "Bạn không có quyền xóa review này"
            );
        }

        System.out.println(
                "========== DELETE REVIEW =========="
        );

        System.out.println(
                "reviewId = " + reviewId
        );

        System.out.println(
                "userId = " + review.getUserId()
        );

        System.out.println(
                "bookId = " + review.getBookId()
        );

        System.out.println(
                "==================================="
        );


        deleteReviewTree(reviewId);
    }


    // ======================================================
    // XÓA ĐỆ QUY TOÀN BỘ CÂY REVIEW
    // ======================================================

    private void deleteReviewTree(
            Long reviewId
    ) {

        // --------------------------------------------------
        // 1. Tìm review hiện tại
        // --------------------------------------------------

        Review review =
                repository.findById(reviewId)
                        .orElse(null);

        if (review == null) {
            return;
        }


        // --------------------------------------------------
        // 2. Lưu parent trước khi xóa
        // --------------------------------------------------

        Long parentReviewId =
                review.getParentReviewId();


        // --------------------------------------------------
        // 3. Tìm tất cả reply trực tiếp
        // --------------------------------------------------

        List<Review> replies =
                repository.findByParentReviewId(
                        reviewId
                );


        // --------------------------------------------------
        // 4. Xóa toàn bộ cây con
        // --------------------------------------------------

        for (Review reply : replies) {

            deleteReviewTree(
                    reply.getReviewId()
            );
        }


        // --------------------------------------------------
        // 5. Xóa REPORT
        // --------------------------------------------------

        reportRepository.deleteByReviewId(
                reviewId
        );


        // --------------------------------------------------
        // 6. Xóa LIKE
        // --------------------------------------------------

        reviewLikeRepository.deleteByReviewId(
                reviewId
        );


        // --------------------------------------------------
        // 7. Xóa REVIEW
        // --------------------------------------------------

        repository.deleteById(
                reviewId
        );


        // --------------------------------------------------
        // 8. Giảm replyCount của REVIEW CHA
        // --------------------------------------------------

        if (parentReviewId != null) {

            Review parentReview =
                    repository.findById(
                            parentReviewId
                    ).orElse(null);

            if (parentReview != null) {

                Long currentCount =
                        parentReview.getReplyCount();

                if (currentCount != null
                        && currentCount > 0) {

                    parentReview.setReplyCount(
                            currentCount - 1
                    );

                    repository.save(parentReview);
                }
            }
        }
    }
}