package com.example.mtc.controller;

import com.example.mtc.entity.ReadingHistory;
import com.example.mtc.service.ReadingHistoryService;
import org.springframework.web.bind.annotation.*;
import com.example.mtc.security.CurrentUserService;

import java.util.List;

@RestController
@RequestMapping("/api/reading-history")
public class ReadingHistoryController {

    private final CurrentUserService currentUserService;

    private final ReadingHistoryService service;

    public ReadingHistoryController(
            ReadingHistoryService service,
            CurrentUserService currentUserService
    ) {
        this.service = service;
        this.currentUserService = currentUserService;
    }


    // ======================================================
    // LẤY LỊCH SỬ ĐỌC
    // ======================================================

    @GetMapping("/user/{userId}")
    public List<ReadingHistory> getHistory(
            @PathVariable Long userId
    ) {

        return service.getByUserId(userId);
    }


    // ======================================================
    // LƯU TIẾN ĐỘ ĐỌC
    // ======================================================

    @PostMapping(
            "/user/{userId}/book/{bookId}"
    )
    public ReadingHistory saveHistory(

            @PathVariable Long userId,

            @PathVariable Long bookId,

            @RequestParam Long chapterId,

            @RequestParam Double progress

    ) {

        return service.saveHistory(
                userId,
                bookId,
                chapterId,
                progress
        );
    }

    // ======================================================
    // LẤY LỊCH SỬ ĐỌC CỦA USER ĐANG ĐĂNG NHẬP
    // ======================================================

    @GetMapping("/me")
    public List<ReadingHistory> getMyHistory() {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.getByUserId(userId);
    }
}