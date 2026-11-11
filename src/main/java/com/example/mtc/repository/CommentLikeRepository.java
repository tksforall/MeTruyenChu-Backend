package com.example.mtc.repository;

import com.example.mtc.entity.CommentLike;
import com.example.mtc.entity.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository
        extends JpaRepository<
        CommentLike,
        CommentLikeId> {

    List<CommentLike> findByCommentId(
            Long commentId
    );

    boolean existsByCommentIdAndUserId(
            Long commentId,
            Long userId
    );

    void deleteByCommentIdAndUserId(
            Long commentId,
            Long userId
    );

    void deleteByCommentId(
            Long commentId
    );
}