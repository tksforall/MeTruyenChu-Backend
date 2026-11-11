package com.example.mtc.controller;

import com.example.mtc.entity.Comment;

import com.example.mtc.service.CommentService;
import org.springframework.web.bind.annotation.*;
import com.example.mtc.security.CurrentUserService;
import com.example.mtc.service.CommentLikeService;
import com.example.mtc.entity.CommentLike;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentLikeService likeService;
    private final CurrentUserService currentUserService;

    private final CommentService service;


    public CommentController(
            CommentService service,
            CommentLikeService likeService,
            CurrentUserService currentUserService
    ) {

        this.service = service;
        this.likeService = likeService;
        this.currentUserService = currentUserService;
    }


    // ======================================================
    // LẤY COMMENT THEO ID
    // ======================================================

    @GetMapping("/{commentId}")
    public Comment getCommentById(
            @PathVariable Long commentId
    ) {

        return service.getCommentById(
                commentId
        );
    }


    // ======================================================
    // TẤT CẢ COMMENT CỦA TRUYỆN
    // ======================================================

    @GetMapping("/book/{bookId}")
    public List<Comment> getByBook(
            @PathVariable Long bookId
    ) {

        return service.getByBookId(bookId);
    }


    // ======================================================
    // COMMENT CHÍNH
    // ======================================================

    @GetMapping("/book/{bookId}/main")
    public List<Comment> getMainComments(
            @PathVariable Long bookId
    ) {

        return service.getMainComments(
                bookId
        );
    }


    // ======================================================
    // REPLY
    // ======================================================

    @GetMapping("/{commentId}/replies")
    public List<Comment> getReplies(
            @PathVariable Long commentId
    ) {

        return service.getReplies(
                commentId
        );
    }


    // ======================================================
    // COMMENT CỦA USER
    // ======================================================

    @GetMapping("/user/{userId}")
    public List<Comment> getByUser(
            @PathVariable Long userId
    ) {

        return service.getByUserId(
                userId
        );
    }


    // ======================================================
    // COMMENT CỦA CHAPTER
    // ======================================================

    @GetMapping("/chapter/{chapterId}")
    public List<Comment> getByChapter(
            @PathVariable Long chapterId
    ) {

        return service.getByChapterId(
                chapterId
        );
    }


    // ======================================================
    // THÊM COMMENT
    // ======================================================

    @PostMapping
    public Comment createComment(
            @RequestBody Comment comment
    ) {

        return service.createComment(
                comment
        );
    }


    // ======================================================
    // SỬA COMMENT
    // ======================================================

    @PutMapping("/{commentId}")
    public Comment updateComment(

            @PathVariable Long commentId,

            @RequestParam String content

    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.updateComment(
                commentId,
                content,
                userId
        );
    }



    // ======================================================
    // XÓA COMMENT
    // ======================================================

    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @PathVariable Long commentId
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        service.deleteComment(
                commentId,
                userId
        );
    }

    // ======================================================
    // LIKE COMMENT
    // ======================================================

    @PostMapping("/{commentId}/like")
    public CommentLike likeComment(
            @PathVariable Long commentId
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        return likeService.like(
                commentId,
                userId
        );
    }

    // ======================================================
    // UNLIKE COMMENT
    // ======================================================

    @DeleteMapping("/{commentId}/like")
    public void unlikeComment(
            @PathVariable Long commentId
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        likeService.unlike(
                commentId,
                userId
        );
    }

    // ======================================================
    // LẤY LIKE CỦA COMMENT
    // ======================================================

    @GetMapping("/{commentId}/likes")
    public List<CommentLike> getLikes(
            @PathVariable Long commentId
    ) {

        return likeService.getByCommentId(
                commentId
        );
    }


}