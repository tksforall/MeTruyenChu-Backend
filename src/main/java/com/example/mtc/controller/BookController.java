package com.example.mtc.controller;

import com.example.mtc.entity.Book;
import com.example.mtc.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.mtc.dto.BookFilterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    // ==================================================
    // CHI TIẾT TRUYỆN
    // ==================================================

    @GetMapping("/{id}")
    public Book getBookById(
            @PathVariable Long id
    ) {
        return bookService.getBookById(id);
    }


    // ==================================================
    // ĐỀ CỬ TRUYỆN
    // ==================================================

    @PostMapping("/{bookId}/recommend")
    public Book recommendBook(
            @PathVariable Long bookId
    ) {
        return bookService.recommendBook(bookId);
    }


    // ==================================================
    // SEARCH
    // ==================================================

    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam String keyword
    ) {
        return bookService.searchBooks(keyword);
    }

    @PostMapping("/filter")
    public List<Book> filterBooks(
            @RequestBody BookFilterRequest request
    ) {
        return bookService.filterBooks(request);
    }



    // ==================================================
    // TẤT CẢ TRUYỆN
    // ==================================================

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    // ==================================================
    // MỚI NHẤT
    // Toàn bộ truyện, UPDATED_AT giảm dần
    // ==================================================

    @GetMapping("/latest")
    public List<Book> getLatestBooks() {
        return bookService.getLatestBooks();
    }


    // ==================================================
    // ĐỀ CỬ - HOME
    // Top 6
    // ==================================================

    @GetMapping("/recommended")
    public List<Book> getRecommendedBooks() {
        return bookService.getRecommendedBooks();
    }


    // ==================================================
    // ĐỀ CỬ - TRANG ĐỀ CỬ
    // Toàn bộ
    // ==================================================

    @GetMapping("/recommended/all")
    public List<Book> getAllRecommendedBooks() {
        return bookService.getAllRecommendedBooks();
    }


    // ==================================================
    // MỚI ĐĂNG - HOME
    // Top 6
    // ==================================================

    @GetMapping("/new-released")
    public List<Book> getNewReleasedBooks() {
        return bookService.getNewReleasedBooks();
    }


    // ==================================================
    // MỚI ĐĂNG - TRANG MỚI ĐĂNG
    // Toàn bộ
    // ==================================================

    @GetMapping("/new-released/all")
    public List<Book> getAllNewReleasedBooks() {
        return bookService.getAllNewReleasedBooks();
    }


    // ==================================================
    // MỚI HOÀN THÀNH - HOME
    // Top 6
    // ==================================================

    @GetMapping("/completed")
    public List<Book> getCompletedBooks() {
        return bookService.getCompletedBooks();
    }


    // ==================================================
    // MỚI HOÀN THÀNH - TRANG MỚI HOÀN THÀNH
    // Toàn bộ
    // ==================================================

    @GetMapping("/completed/all")
    public List<Book> getAllCompletedBooks() {
        return bookService.getAllCompletedBooks();
    }






}