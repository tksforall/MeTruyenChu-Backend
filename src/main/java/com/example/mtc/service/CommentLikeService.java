package com.example.mtc.service;

import com.example.mtc.entity.Comment;
import com.example.mtc.entity.CommentLike;
import com.example.mtc.repository.CommentLikeRepository;
import com.example.mtc.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentLikeService {

    private final CommentLikeRepository repository;
    private final CommentRepository commentRepository;


    public CommentLikeService(
            CommentLikeRepository repository,
            CommentRepository commentRepository
    ) {

        this.repository = repository;
        this.commentRepository = commentRepository;
    }


    // ======================================================
    // LẤY LIKE CỦA COMMENT
    // ======================================================

    public List<CommentLike> getByCommentId(
            Long commentId
    ) {

        return repository.findByCommentId(
                commentId
        );
    }


    // ======================================================
    // LIKE COMMENT
    // ======================================================

    @Transactional
    public CommentLike like(
            Long commentId,
            Long userId
    ) {

        // Kiểm tra comment có tồn tại không
        Comment comment =
                commentRepository.findById(
                        commentId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy comment"
                        )
                );


        // Kiểm tra user đã like chưa
        if (
                repository.existsByCommentIdAndUserId(
                        commentId,
                        userId
                )
        ) {

            throw new RuntimeException(
                    "User đã like comment này"
            );
        }


        // Tạo like
        CommentLike like =
                new CommentLike();

        like.setCommentId(commentId);

        like.setUserId(userId);

        like.setCreatedAt(
                LocalDateTime.now()
        );


        CommentLike savedLike =
                repository.save(like);


        // Tăng LIKE_COUNT
        if (comment.getLikeCount() == null) {

            comment.setLikeCount(0L);
        }

        comment.setLikeCount(
                comment.getLikeCount() + 1
        );

        commentRepository.save(comment);


        return savedLike;
    }


    // ======================================================
    // UNLIKE COMMENT
    // ======================================================

    @Transactional
    public void unlike(
            Long commentId,
            Long userId
    ) {

        Comment comment =
                commentRepository.findById(
                        commentId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy comment"
                        )
                );


        // Kiểm tra user có like không
        boolean exists =
                repository.existsByCommentIdAndUserId(
                        commentId,
                        userId
                );


        if (!exists) {

            throw new RuntimeException(
                    "User chưa like comment này"
            );
        }


        // Xóa like
        repository.deleteByCommentIdAndUserId(
                commentId,
                userId
        );


        // Giảm LIKE_COUNT
        if (
                comment.getLikeCount() != null
                        && comment.getLikeCount() > 0
        ) {

            comment.setLikeCount(
                    comment.getLikeCount() - 1
            );

            commentRepository.save(comment);
        }
    }
}