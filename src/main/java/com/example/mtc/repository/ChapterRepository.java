package com.example.mtc.repository;

import com.example.mtc.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository
        extends JpaRepository<Chapter, Long> {

    // Lấy toàn bộ chapter của một truyện
    List<Chapter> findByBookIdOrderByChapterNumberAsc(Long bookId);

    // Lấy một chapter cụ thể của một truyện
    Chapter findByBookIdAndChapterNumber(
            Long bookId,
            Integer chapterNumber
    );
}