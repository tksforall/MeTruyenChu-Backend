package com.example.mtc.service;

import com.example.mtc.entity.Comment;
import com.example.mtc.repository.CommentRepository;
import com.example.mtc.repository.CommentLikeRepository;
import com.example.mtc.repository.ReportRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository repository;
    private final CommentLikeRepository commentLikeRepository;
    private final ReportRepository reportRepository;


    public CommentService(
            CommentRepository repository,
            CommentLikeRepository commentLikeRepository,
            ReportRepository reportRepository
    ) {

        this.repository = repository;
        this.commentLikeRepository = commentLikeRepository;
        this.reportRepository = reportRepository;
    }

    // ======================================================
    // LẤY COMMENT THEO ID
    // ======================================================

    public Comment getCommentById(
            Long commentId
    ) {

        return repository
                .findById(commentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy comment với ID: "
                                        + commentId
                        )
                );
    }


    // ======================================================
    // LẤY COMMENT CỦA TRUYỆN
    // ======================================================

    public List<Comment> getByBookId(
            Long bookId
    ) {

        return repository.findByBookId(bookId);
    }


    // ======================================================
    // LẤY COMMENT CHÍNH
    // ======================================================

    public List<Comment> getMainComments(
            Long bookId
    ) {

        return repository
                .findByBookIdAndParentCommentIdIsNull(
                        bookId
                );
    }


    // ======================================================
    // LẤY REPLY
    // ======================================================

    public List<Comment> getReplies(
            Long commentId
    ) {

        return repository.findByParentCommentId(
                commentId
        );
    }


    // ======================================================
    // LẤY COMMENT CỦA CHƯƠNG
    // ======================================================

    public List<Comment> getByChapterId(
            Long chapterId
    ) {

        return repository.findByChapterId(
                chapterId
        );
    }


    // ======================================================
    // LẤY COMMENT CỦA USER
    // ======================================================

    public List<Comment> getByUserId(
            Long userId
    ) {

        return repository.findByUserId(
                userId
        );
    }


    // ======================================================
    // THÊM COMMENT
    // ======================================================


    @Transactional
    public Comment createComment(
            Comment comment
    ) {

        comment.setCreatedAt(
                LocalDateTime.now()
        );

        comment.setUpdatedAt(
                LocalDateTime.now()
        );

        if (comment.getLikeCount() == null) {
            comment.setLikeCount(0L);
        }

        if (comment.getReplyCount() == null) {
            comment.setReplyCount(0L);
        }

        // ==================================================
        // NẾU LÀ REPLY → TĂNG REPLY_COUNT CỦA COMMENT CHA
        // ==================================================

        if (comment.getParentCommentId() != null) {

            Comment parentComment =
                    repository.findById(
                            comment.getParentCommentId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Không tìm thấy comment cha"
                            )
                    );

            if (parentComment.getReplyCount() == null) {
                parentComment.setReplyCount(0L);
            }

            parentComment.setReplyCount(
                    parentComment.getReplyCount() + 1
            );

            repository.save(parentComment);
        }

        return repository.save(comment);
    }


    // ======================================================
    // SỬA COMMENT
    // ======================================================

    public Comment updateComment(
            Long commentId,
            String content,
            Long userId
    ) {

        Comment comment = repository
                .findById(commentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy comment"
                        )
                );

        // Kiểm tra quyền sở hữu
        if (!comment.getUserId().equals(userId)) {

            throw new RuntimeException(
                    "Bạn không có quyền sửa comment này"
            );
        }

        comment.setContent(content);

        comment.setUpdatedAt(
                LocalDateTime.now()
        );

        return repository.save(comment);
    }


    // ======================================================
    // XÓA COMMENT + REPLY + LIKE + REPORT
    // ======================================================

    @Transactional
    public void deleteComment(
            Long commentId,
            Long userId
    ) {

        Comment comment = repository
                .findById(commentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy comment"
                        )
                );

        if (!comment.getUserId().equals(userId)) {

            throw new RuntimeException(
                    "Bạn không có quyền xóa comment này"
            );
        }

        deleteCommentTree(commentId);
    }


    // ======================================================
    // XÓA ĐỆ QUY TOÀN BỘ CÂY COMMENT
    // ======================================================

    private void deleteCommentTree(Long commentId) {

        Comment comment = repository
                .findById(commentId)
                .orElse(null);

        if (comment == null) {
            return;
        }

        // Lưu parent trước khi xóa
        Long parentCommentId =
                comment.getParentCommentId();

        // Lấy toàn bộ reply trực tiếp
        List<Comment> replies =
                repository.findByParentCommentId(commentId);

        // Xóa đệ quy toàn bộ reply con
        for (Comment reply : replies) {

            deleteCommentTree(
                    reply.getCommentId()
            );
        }

        // Xóa toàn bộ like của comment hiện tại
        commentLikeRepository.deleteByCommentId(
                commentId
        );

        // Xóa comment
        repository.deleteById(
                commentId
        );

        // Nếu đây là reply thì giảm replyCount của cha
        if (parentCommentId != null) {

            Comment parent =
                    repository
                            .findById(parentCommentId)
                            .orElse(null);

            if (parent != null) {

                Long currentCount =
                        parent.getReplyCount();

                if (currentCount != null &&
                        currentCount > 0) {

                    parent.setReplyCount(
                            currentCount - 1
                    );

                    repository.save(parent);
                }
            }
        }
    }
}