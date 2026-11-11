package com.example.mtc.repository;

import com.example.mtc.entity.BookGenre;
import com.example.mtc.entity.BookGenreId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookGenreRepository
        extends JpaRepository<BookGenre, BookGenreId> {

    List<BookGenre> findByBook_BookId(Long bookId);
}