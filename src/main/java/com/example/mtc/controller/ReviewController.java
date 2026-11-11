package com.example.mtc.controller;

import com.example.mtc.dto.ReviewResponse;
import com.example.mtc.entity.Review;
import com.example.mtc.security.CurrentUserService;
import com.example.mtc.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;
    private final CurrentUserService currentUserService;

    public ReviewController(
            ReviewService service,
            CurrentUserService currentUserService
    ) {
        this.service = service;
        this.currentUserService = currentUserService;
    }

    // ======================================================
    // LẤY REVIEW THEO ID
    // ======================================================

    @GetMapping("/{reviewId}")
    public Review getReviewById(
            @PathVariable Long reviewId
    ) {

        return service.getReviewById(
                reviewId
        );
    }


    // ======================================================
    // TẤT CẢ REVIEW CỦA TRUYỆN
    // ======================================================

    @GetMapping("/book/{bookId}")
    public List<Review> getByBook(
            @PathVariable Long bookId
    ) {

        return service.getByBookId(bookId);
    }


    // ======================================================
    // REVIEW CHÍNH
    // ======================================================

    @GetMapping("/book/{bookId}/main")
    public List<ReviewResponse> getMainReviews(
            @PathVariable Long bookId
    ) {

        return service.getMainReviews(bookId);
    }


    // ======================================================
    // REPLY
    // ======================================================

    @GetMapping("/{reviewId}/replies")
    public List<Review> getReplies(
            @PathVariable Long reviewId
    ) {

        return service.getReplies(reviewId);
    }


    // ======================================================
    // REVIEW CỦA USER
    // ======================================================

    @GetMapping("/user/{userId}")
    public List<Review> getByUser(
            @PathVariable Long userId
    ) {

        return service.getByUserId(userId);
    }


    // ======================================================
    // THÊM REVIEW
    // USER_ID LẤY TỪ JWT
    // ======================================================

    @PostMapping
    public Review createReview(
            @RequestBody Review review
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.createReview(
                review,
                userId
        );
    }


    // ======================================================
    // SỬA REVIEW
    // ======================================================

    @PutMapping("/{reviewId}")
    public Review updateReview(

            @PathVariable Long reviewId,

            @RequestParam String content,

            @RequestParam(required = false) Double rating

    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.updateReview(
                reviewId,
                content,
                rating,
                userId
        );
    }


    // ======================================================
    // XÓA REVIEW
    // ======================================================

    @DeleteMapping("/{reviewId}")
    public void deleteReview(
            @PathVariable Long reviewId
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        service.deleteReview(
                reviewId,
                userId
        );
    }
}