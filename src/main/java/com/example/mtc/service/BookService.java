package com.example.mtc.service;

import com.example.mtc.entity.Book;
import com.example.mtc.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.example.mtc.dto.BookFilterRequest;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ==================================================
    // CHI TIẾT TRUYỆN
    // ==================================================

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Không tìm thấy truyện với ID: " + bookId
                ));
    }


    // ==================================================
    // ĐỀ CỬ TRUYỆN
    // ==================================================

    public Book recommendBook(Long bookId) {
        Book book = getBookById(bookId);
        Long current = book.getRecommendationCount();
        book.setRecommendationCount(current == null ? 1L : current + 1L);
        return bookRepository.save(book);
    }


    // ==================================================
    // TẤT CẢ TRUYỆN
    // ==================================================

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    // ==================================================
    // MỚI NHẤT
    // Dùng UPDATED_AT
    // Trang Mới Nhất lấy toàn bộ
    // ==================================================

    public List<Book> getLatestBooks() {
        return bookRepository.findAllByOrderByUpdatedAtDesc();
    }


    // ==================================================
    // ĐỀ CỬ - HOME
    // Chỉ lấy Top 6
    // ==================================================

    public List<Book> getRecommendedBooks() {
        return bookRepository.findTop6ByOrderByRecommendationCountDesc();
    }


    // ==================================================
    // ĐỀ CỬ - TRANG ĐỀ CỬ
    // Lấy toàn bộ
    // ==================================================

    public List<Book> getAllRecommendedBooks() {
        return bookRepository.findAllByOrderByRecommendationCountDesc();
    }


    // ==================================================
    // MỚI ĐĂNG - HOME
    // Chỉ lấy Top 6
    // ==================================================

    public List<Book> getNewReleasedBooks() {
        return bookRepository.findTop6ByOrderByCreatedAtDesc();
    }


    // ==================================================
    // MỚI ĐĂNG - TRANG MỚI ĐĂNG
    // Lấy toàn bộ
    // ==================================================

    public List<Book> getAllNewReleasedBooks() {
        return bookRepository.findAllByOrderByCreatedAtDesc();
    }


    // ==================================================
    // MỚI HOÀN THÀNH - HOME
    // Chỉ lấy Top 6
    // ==================================================

    public List<Book> getCompletedBooks() {
        return bookRepository.findTop6ByStatusIgnoreCaseOrderByUpdatedAtDesc("Completed");
    }


    // ==================================================
    // MỚI HOÀN THÀNH - TRANG RIÊNG
    // Lấy toàn bộ
    // ==================================================

    public List<Book> getAllCompletedBooks() {
        return bookRepository
                .findAllByStatusOrderByUpdatedAtDesc("Completed");
    }


    // ==================================================
    // TÌM KIẾM TRUYỆN
    // ==================================================

    public List<Book> searchBooks(String keyword) {

        return bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }



    // ==================================================
// FILTER TRUYỆN
// ==================================================

    public List<Book> filterBooks(BookFilterRequest request) {

        return bookRepository.filterBooks(

                request.getSort(),

                request.getStatus(),

                request.getChapterRange(),

                request.getPublishDate(),

                request.getGenres(),

                request.getPersonalities(),

                request.getWorlds(),

                request.getTags()
        );
    }



}