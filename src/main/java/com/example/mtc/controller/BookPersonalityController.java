package com.example.mtc.controller;

import com.example.mtc.entity.BookPersonality;
import com.example.mtc.service.BookPersonalityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-personalities")
public class BookPersonalityController {

    private final BookPersonalityService bookPersonalityService;

    public BookPersonalityController(
            BookPersonalityService bookPersonalityService
    ) {
        this.bookPersonalityService = bookPersonalityService;
    }

    @GetMapping
    public List<BookPersonality> getAllBookPersonalities() {
        return bookPersonalityService.getAllBookPersonalities();
    }

    @GetMapping("/book/{bookId}")
    public List<BookPersonality> getPersonalitiesByBookId(
            @PathVariable Long bookId
    ) {
        return bookPersonalityService
                .getPersonalitiesByBookId(bookId);
    }

    @GetMapping("/personality/{personalityId}")
    public List<BookPersonality> getBooksByPersonalityId(
            @PathVariable Long personalityId
    ) {
        return bookPersonalityService
                .getBooksByPersonalityId(personalityId);
    }

    @PostMapping
    public BookPersonality addBookPersonality(
            @RequestParam Long bookId,
            @RequestParam Long personalityId
    ) {
        return bookPersonalityService
                .addBookPersonality(bookId, personalityId);
    }

    @DeleteMapping
    public void deleteBookPersonality(
            @RequestParam Long bookId,
            @RequestParam Long personalityId
    ) {
        bookPersonalityService
                .deleteBookPersonality(bookId, personalityId);
    }
}