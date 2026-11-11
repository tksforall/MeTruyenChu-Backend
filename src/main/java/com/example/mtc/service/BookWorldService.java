package com.example.mtc.service;

import com.example.mtc.entity.BookWorld;
import com.example.mtc.entity.BookWorldId;
import com.example.mtc.repository.BookWorldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookWorldService {

    private final BookWorldRepository bookWorldRepository;

    public BookWorldService(
            BookWorldRepository bookWorldRepository
    ) {
        this.bookWorldRepository = bookWorldRepository;
    }

    // Lấy tất cả liên kết Book - World
    public List<BookWorld> getAllBookWorlds() {
        return bookWorldRepository.findAll();
    }

    // Lấy World của một Book
    public List<BookWorld> getWorldsByBookId(Long bookId) {
        return bookWorldRepository.findByIdBookId(bookId);
    }

    // Lấy Book của một World
    public List<BookWorld> getBooksByWorldId(Long worldId) {
        return bookWorldRepository.findByIdWorldId(worldId);
    }

    // Thêm liên kết Book - World
    public BookWorld addBookWorld(Long bookId, Long worldId) {

        BookWorldId id = new BookWorldId(bookId, worldId);

        BookWorld bookWorld = new BookWorld(id);

        return bookWorldRepository.save(bookWorld);
    }

    // Xóa liên kết Book - World
    public void deleteBookWorld(Long bookId, Long worldId) {

        BookWorldId id = new BookWorldId(bookId, worldId);

        bookWorldRepository.deleteById(id);
    }
}