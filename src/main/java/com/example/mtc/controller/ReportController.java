package com.example.mtc.controller;

import com.example.mtc.entity.Report;
import com.example.mtc.security.CurrentUserService;
import com.example.mtc.service.ReportService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService service;
    private final CurrentUserService currentUserService;


    public ReportController(
            ReportService service,
            CurrentUserService currentUserService
    ) {

        this.service = service;
        this.currentUserService = currentUserService;
    }


    // ======================================================
    // TẠO REPORT
    // ======================================================

    @PostMapping
    public Report createReport(
            @RequestBody Report report
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.createReport(
                report,
                userId
        );
    }


    // ======================================================
    // REPORT CỦA REVIEW
    // ======================================================

    @GetMapping("/review/{reviewId}")
    public List<Report> getByReview(
            @PathVariable Long reviewId
    ) {

        return service.getByReviewId(
                reviewId
        );
    }


    // ======================================================
    // REPORT CỦA COMMENT
    // ======================================================

    @GetMapping("/comment/{commentId}")
    public List<Report> getByComment(
            @PathVariable Long commentId
    ) {

        return service.getByCommentId(
                commentId
        );
    }


    // ======================================================
    // REPORT CỦA CHAPTER
    // ======================================================

    @GetMapping("/chapter/{chapterId}")
    public List<Report> getByChapter(
            @PathVariable Long chapterId
    ) {

        return service.getByChapterId(
                chapterId
        );
    }
}