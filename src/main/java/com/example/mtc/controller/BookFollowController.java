package com.example.mtc.controller;

import com.example.mtc.entity.BookFollow;
import com.example.mtc.service.BookFollowService;
import org.springframework.web.bind.annotation.*;
import com.example.mtc.security.CurrentUserService;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
public class BookFollowController {

    private final CurrentUserService currentUserService;

    private final BookFollowService service;

    public BookFollowController(
            BookFollowService service,
            CurrentUserService currentUserService
    ) {
        this.service = service;
        this.currentUserService = currentUserService;
    }

    @GetMapping("/user/{userId}")
    public List<BookFollow> getUserFollows(
            @PathVariable Long userId
    ) {

        return service.getByUserId(userId);
    }

    @PostMapping("/user/{userId}/book/{bookId}")
    public BookFollow follow(
            @PathVariable Long userId,
            @PathVariable Long bookId
    ) {

        return service.follow(
                userId,
                bookId
        );
    }

    @DeleteMapping("/user/{userId}/book/{bookId}")
    public void unfollow(
            @PathVariable Long userId,
            @PathVariable Long bookId
    ) {

        service.unfollow(
                userId,
                bookId
        );
    }

    @GetMapping("/me")
    public List<BookFollow> getMyFollows() {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.getByUserId(userId);
    }

}