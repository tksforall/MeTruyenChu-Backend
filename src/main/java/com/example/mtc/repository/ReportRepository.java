package com.example.mtc.repository;

import com.example.mtc.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository
        extends JpaRepository<Report, Long> {

    List<Report> findByReviewId(Long reviewId);

    List<Report> findByCommentId(Long commentId);

    List<Report> findByChapterId(Long chapterId);


    @Modifying
    @Query("""
        DELETE FROM Report r
        WHERE r.reviewId = :reviewId
    """)
    void deleteByReviewId(
            @Param("reviewId") Long reviewId
    );


    @Modifying
    @Query("""
        DELETE FROM Report r
        WHERE r.commentId = :commentId
    """)
    void deleteByCommentId(
            @Param("commentId") Long commentId
    );


    @Modifying
    @Query("""
        DELETE FROM Report r
        WHERE r.chapterId = :chapterId
    """)
    void deleteByChapterId(
            @Param("chapterId") Long chapterId
    );
}