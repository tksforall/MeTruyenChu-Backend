package com.example.mtc.controller;

import com.example.mtc.entity.BookWorld;
import com.example.mtc.service.BookWorldService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-worlds")
public class BookWorldController {

    private final BookWorldService bookWorldService;

    public BookWorldController(
            BookWorldService bookWorldService
    ) {
        this.bookWorldService = bookWorldService;
    }

    // GET /api/book-worlds
    @GetMapping
    public List<BookWorld> getAllBookWorlds() {
        return bookWorldService.getAllBookWorlds();
    }

    // GET /api/book-worlds/book/1
    @GetMapping("/book/{bookId}")
    public List<BookWorld> getWorldsByBookId(
            @PathVariable Long bookId
    ) {
        return bookWorldService.getWorldsByBookId(bookId);
    }

    // GET /api/book-worlds/world/1
    @GetMapping("/world/{worldId}")
    public List<BookWorld> getBooksByWorldId(
            @PathVariable Long worldId
    ) {
        return bookWorldService.getBooksByWorldId(worldId);
    }

    // POST /api/book-worlds?bookId=1&worldId=2
    @PostMapping
    public BookWorld addBookWorld(
            @RequestParam Long bookId,
            @RequestParam Long worldId
    ) {
        return bookWorldService.addBookWorld(bookId, worldId);
    }

    // DELETE /api/book-worlds?bookId=1&worldId=2
    @DeleteMapping
    public void deleteBookWorld(
            @RequestParam Long bookId,
            @RequestParam Long worldId
    ) {
        bookWorldService.deleteBookWorld(bookId, worldId);
    }
}
