package com.example.mtc.service;

import com.example.mtc.entity.BookGenre;
import com.example.mtc.repository.BookGenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookGenreService {

    private final BookGenreRepository bookGenreRepository;

    public BookGenreService(BookGenreRepository bookGenreRepository) {
        this.bookGenreRepository = bookGenreRepository;
    }

    public List<BookGenre> getGenresByBookId(Long bookId) {
        return bookGenreRepository.findByBook_BookId(bookId);
    }
}