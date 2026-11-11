package com.example.mtc.repository;

import com.example.mtc.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {

    // Tất cả comment của một truyện
    List<Comment> findByBookId(Long bookId);

    // Comment của một user
    List<Comment> findByUserId(Long userId);

    // Comment của một chương
    List<Comment> findByChapterId(Long chapterId);

    // Chỉ lấy comment chính
    List<Comment> findByBookIdAndParentCommentIdIsNull(
            Long bookId
    );

    // Lấy các reply
    List<Comment> findByParentCommentId(
            Long parentCommentId
    );
}