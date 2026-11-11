package com.example.mtc.repository;

import com.example.mtc.entity.BookFollow;
import com.example.mtc.entity.BookFollowId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookFollowRepository
        extends JpaRepository<BookFollow, BookFollowId> {

    List<BookFollow> findByUserId(Long userId);

    List<BookFollow> findByBookId(Long bookId);

    boolean existsByUserIdAndBookId(
            Long userId,
            Long bookId
    );

    void deleteByUserIdAndBookId(
            Long userId,
            Long bookId
    );
}