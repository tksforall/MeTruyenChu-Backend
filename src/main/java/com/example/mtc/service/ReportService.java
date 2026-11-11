package com.example.mtc.service;

import com.example.mtc.entity.Report;
import com.example.mtc.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository repository;

    public ReportService(
            ReportRepository repository
    ) {
        this.repository = repository;
    }


    // ======================================================
    // TẠO REPORT
    // ======================================================

    public Report createReport(
            Report report,
            Long userId
    ) {

        // ==========================================
        // CHỈ ĐƯỢC REPORT 1 ĐỐI TƯỢNG
        // ==========================================

        int targetCount = 0;

        if (report.getReviewId() != null) {
            targetCount++;
        }

        if (report.getCommentId() != null) {
            targetCount++;
        }

        if (report.getChapterId() != null) {
            targetCount++;
        }


        if (targetCount == 0) {

            throw new RuntimeException(
                    "Report phải có reviewId, commentId hoặc chapterId"
            );
        }


        if (targetCount > 1) {

            throw new RuntimeException(
                    "Một report chỉ được chứa một đối tượng"
            );
        }


        // ==========================================
        // KHÔNG CHO CLIENT TỰ QUYẾT ĐỊNH
        // ==========================================

        report.setReportId(null);

        report.setUserId(userId);

        report.setStatus("PENDING");

        report.setCreatedAt(
                LocalDateTime.now()
        );


        return repository.save(report);
    }


    // ======================================================
    // LẤY REPORT REVIEW
    // ======================================================

    public List<Report> getByReviewId(
            Long reviewId
    ) {

        return repository.findByReviewId(
                reviewId
        );
    }


    // ======================================================
    // LẤY REPORT COMMENT
    // ======================================================

    public List<Report> getByCommentId(
            Long commentId
    ) {

        return repository.findByCommentId(
                commentId
        );
    }


    // ======================================================
    // LẤY REPORT CHAPTER
    // ======================================================

    public List<Report> getByChapterId(
            Long chapterId
    ) {

        return repository.findByChapterId(
                chapterId
        );
    }
}