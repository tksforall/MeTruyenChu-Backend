package com.example.mtc.service;

import com.example.mtc.entity.BookLibrary;
import com.example.mtc.entity.BookLibraryId;
import com.example.mtc.repository.BookLibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookLibraryService {

    private final BookLibraryRepository repository;

    public BookLibraryService(
            BookLibraryRepository repository
    ) {
        this.repository = repository;
    }

    public List<BookLibrary> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public BookLibrary addBook(
            Long userId,
            Long bookId
    ) {

        if (repository.existsByUserIdAndBookId(
                userId,
                bookId
        )) {
            throw new RuntimeException(
                    "Truyện đã có trong thư viện"
            );
        }

        BookLibrary library = new BookLibrary();

        library.setUserId(userId);
        library.setBookId(bookId);
        library.setCreatedAt(LocalDateTime.now());

        return repository.save(library);
    }

    @Transactional
    public void removeBook(
            Long userId,
            Long bookId
    ) {

        repository.deleteByUserIdAndBookId(
                userId,
                bookId
        );
    }
}