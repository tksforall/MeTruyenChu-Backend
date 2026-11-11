package com.example.mtc.controller;

import com.example.mtc.entity.BookGenre;
import com.example.mtc.service.BookGenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookGenreController {

    private final BookGenreService bookGenreService;

    public BookGenreController(BookGenreService bookGenreService) {
        this.bookGenreService = bookGenreService;
    }

    @GetMapping("/{bookId}/genres")
    public List<BookGenre> getGenresByBookId(
            @PathVariable Long bookId
    ) {
        return bookGenreService.getGenresByBookId(bookId);
    }
}