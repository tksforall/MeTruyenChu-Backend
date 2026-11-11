package com.example.mtc.repository;

import com.example.mtc.entity.BookLibrary;
import com.example.mtc.entity.BookLibraryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookLibraryRepository
        extends JpaRepository<BookLibrary, BookLibraryId> {

    List<BookLibrary> findByUserId(Long userId);

    List<BookLibrary> findByBookId(Long bookId);

    boolean existsByUserIdAndBookId(
            Long userId,
            Long bookId
    );

    void deleteByUserIdAndBookId(
            Long userId,
            Long bookId
    );
}