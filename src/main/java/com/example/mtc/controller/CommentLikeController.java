package com.example.mtc.controller;

import com.example.mtc.entity.CommentLike;
import com.example.mtc.security.CurrentUserService;
import com.example.mtc.service.CommentLikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment-likes")
public class CommentLikeController {

    private final CommentLikeService service;
    private final CurrentUserService currentUserService;

    public CommentLikeController(
            CommentLikeService service,
            CurrentUserService currentUserService
    ) {
        this.service = service;
        this.currentUserService = currentUserService;
    }


    // ======================================================
    // LẤY LIKE CỦA COMMENT
    // ======================================================

    @GetMapping("/comment/{commentId}")
    public List<CommentLike> getLikes(
            @PathVariable Long commentId
    ) {

        return service.getByCommentId(
                commentId
        );
    }


    // ======================================================
    // LIKE - USER ĐANG ĐĂNG NHẬP
    // ======================================================

    @PostMapping("/comment/{commentId}")
    public CommentLike like(
            @PathVariable Long commentId
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.like(
                commentId,
                userId
        );
    }


    // ======================================================
    // UNLIKE - USER ĐANG ĐĂNG NHẬP
    // ======================================================

    @DeleteMapping("/comment/{commentId}")
    public void unlike(
            @PathVariable Long commentId
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        service.unlike(
                commentId,
                userId
        );
    }


    // ======================================================
    // API CŨ - GIỮ LẠI TẠM THỜI
    // ======================================================

    @PostMapping("/comment/{commentId}/user/{userId}")
    public CommentLike likeOld(
            @PathVariable Long commentId,
            @PathVariable Long userId
    ) {

        return service.like(
                commentId,
                userId
        );
    }


    @DeleteMapping("/comment/{commentId}/user/{userId}")
    public void unlikeOld(
            @PathVariable Long commentId,
            @PathVariable Long userId
    ) {

        service.unlike(
                commentId,
                userId
        );
    }
}