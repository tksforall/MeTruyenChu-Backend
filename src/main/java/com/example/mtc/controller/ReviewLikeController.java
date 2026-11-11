package com.example.mtc.controller;

import com.example.mtc.dto.ReviewLikeStatusResponse;
import com.example.mtc.entity.ReviewLike;
import com.example.mtc.security.CurrentUserService;
import com.example.mtc.service.ReviewLikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review-likes")
public class ReviewLikeController {

    private final ReviewLikeService service;
    private final CurrentUserService currentUserService;

    public ReviewLikeController(
            ReviewLikeService service,
            CurrentUserService currentUserService
    ) {

        this.service = service;
        this.currentUserService = currentUserService;
    }


    // ======================================================
    // LẤY TẤT CẢ LIKE CỦA REVIEW
    // ======================================================

    @GetMapping("/review/{reviewId}")
    public List<ReviewLike> getLikes(
            @PathVariable Long reviewId
    ) {

        return service.getByReviewId(
                reviewId
        );
    }


    // ======================================================
    // LIKE REVIEW
    // ======================================================

    @PostMapping("/review/{reviewId}")
    public ReviewLike like(
            @PathVariable Long reviewId
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.like(
                reviewId,
                userId
        );
    }


    // ======================================================
    // UNLIKE REVIEW
    // ======================================================

    @DeleteMapping("/review/{reviewId}")
    public void unlike(
            @PathVariable Long reviewId
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        service.unlike(
                reviewId,
                userId
        );
    }

    // ===========================================================
    // KIỂM TRA STATUS LIKE
    // ===================================================== ======

    @GetMapping("/review/{reviewId}/status")
    public ReviewLikeStatusResponse getLikeStatus(
            @PathVariable Long reviewId
    ) {
        Long userId = currentUserService.getCurrentUserId();

        boolean liked = service.isLiked(
                reviewId,
                userId
        );

        return new ReviewLikeStatusResponse(liked);
    }
}