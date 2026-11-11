package com.example.mtc.repository;

import com.example.mtc.entity.BookTag;
import com.example.mtc.entity.BookTagId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookTagRepository
        extends JpaRepository<BookTag, BookTagId> {

    List<BookTag> findByIdBookId(Long bookId);
}