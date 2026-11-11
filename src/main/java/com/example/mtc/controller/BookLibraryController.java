package com.example.mtc.controller;

import com.example.mtc.entity.BookLibrary;
import com.example.mtc.service.BookLibraryService;
import org.springframework.web.bind.annotation.*;
import com.example.mtc.security.CurrentUserService;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class BookLibraryController {

    private final CurrentUserService currentUserService;

    private final BookLibraryService service;

    public BookLibraryController(
            BookLibraryService service,
            CurrentUserService currentUserService
    ) {
        this.service = service;
        this.currentUserService = currentUserService;
    }

    // Lấy thư viện của user
    @GetMapping("/user/{userId}")
    public List<BookLibrary> getUserLibrary(
            @PathVariable Long userId
    ) {

        return service.getByUserId(userId);
    }

    // Thêm truyện vào thư viện
    @PostMapping("/user/{userId}/book/{bookId}")
    public BookLibrary addBook(
            @PathVariable Long userId,
            @PathVariable Long bookId
    ) {

        return service.addBook(
                userId,
                bookId
        );
    }

    // Xóa truyện khỏi thư viện
    @DeleteMapping("/user/{userId}/book/{bookId}")
    public void removeBook(
            @PathVariable Long userId,
            @PathVariable Long bookId
    ) {

        service.removeBook(
                userId,
                bookId
        );
    }

    // ======================================================
    // LẤY THƯ VIỆN CỦA USER ĐANG ĐĂNG NHẬP
    // ======================================================

    @GetMapping("/me")
    public List<BookLibrary> getMyLibrary() {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.getByUserId(userId);
    }
}