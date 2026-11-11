package com.example.mtc.controller;

import com.example.mtc.entity.Chapter;
import com.example.mtc.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(
            ChapterService chapterService
    ) {
        this.chapterService = chapterService;
    }


    // ======================================================
    // GET ALL
    // ======================================================

    @GetMapping
    public List<Chapter> getAllChapters() {

        return chapterService.getAllChapters();
    }


    // ======================================================
    // GET CHAPTER BY ID
    // ======================================================

    @GetMapping("/{id}")
    public Chapter getChapterById(
            @PathVariable Long id
    ) {

        return chapterService.getChapterById(id);
    }


    // ======================================================
    // GET CHAPTERS BY BOOK
    // ======================================================

    @GetMapping("/book/{bookId}")
    public List<Chapter> getChaptersByBookId(
            @PathVariable Long bookId
    ) {

        return chapterService
                .getChaptersByBookId(bookId);
    }


    // ======================================================
    // GET SPECIFIC CHAPTER
    // ======================================================

    @GetMapping("/book/{bookId}/chapter/{chapterNumber}")
    public Chapter getChapter(
            @PathVariable Long bookId,
            @PathVariable Integer chapterNumber
    ) {

        return chapterService.getChapter(
                bookId,
                chapterNumber
        );
    }


    // ======================================================
    // UPDATE CHAPTER
    // ======================================================

    @PutMapping("/{id}")
    public Chapter updateChapter(
            @PathVariable Long id,
            @RequestParam(required = false) Integer chapterNumber,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content
    ) {

        return chapterService.updateChapter(
                id,
                chapterNumber,
                title,
                content
        );
    }


    // ======================================================
    // TĂNG VIEW
    // ======================================================

    @PostMapping("/{id}/view")
    public Chapter increaseViewCount(
            @PathVariable Long id
    ) {

        return chapterService
                .increaseViewCount(id);
    }
}