package com.example.mtc.service;

import com.example.mtc.entity.Chapter;
import com.example.mtc.repository.ChapterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;

    public ChapterService(
            ChapterRepository chapterRepository
    ) {
        this.chapterRepository = chapterRepository;
    }

    // ======================================================
    // LẤY TẤT CẢ CHAPTER
    // ======================================================

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }


    // ======================================================
    // LẤY CHAPTER THEO ID
    // ======================================================

    public Chapter getChapterById(Long id) {

        return chapterRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy chapter với ID: " + id
                        )
                );
    }


    // ======================================================
    // LẤY TẤT CẢ CHAPTER CỦA MỘT BOOK
    // ======================================================

    public List<Chapter> getChaptersByBookId(Long bookId) {

        return chapterRepository
                .findByBookIdOrderByChapterNumberAsc(bookId);
    }


    // ======================================================
    // LẤY MỘT CHAPTER THEO BOOK + CHAPTER NUMBER
    // ======================================================

    public Chapter getChapter(
            Long bookId,
            Integer chapterNumber
    ) {

        Chapter chapter =
                chapterRepository
                        .findByBookIdAndChapterNumber(
                                bookId,
                                chapterNumber
                        );

        if (chapter == null) {

            throw new RuntimeException(
                    "Không tìm thấy chapter "
                            + chapterNumber
                            + " của book "
                            + bookId
            );
        }

        return chapter;
    }


    // ======================================================
    // CẬP NHẬT CHAPTER
    // ======================================================

    public Chapter updateChapter(
            Long id,
            Integer chapterNumber,
            String title,
            String content
    ) {

        Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy chapter với ID: " + id
                        )
                );

        if (chapterNumber != null) {
            chapter.setChapterNumber(chapterNumber);
        }

        if (title != null && !title.isBlank()) {
            chapter.setTitle(title);
        }

        if (content != null && !content.isBlank()) {
            chapter.setContent(content);
        }

        return chapterRepository.save(chapter);
    }

    // ======================================================
    // TĂNG LƯỢT XEM
    // ======================================================

    public Chapter increaseViewCount(Long chapterId) {

        Chapter chapter = getChapterById(chapterId);

        chapter.setViewCount(
                chapter.getViewCount() + 1
        );

        return chapterRepository.save(chapter);
    }
}