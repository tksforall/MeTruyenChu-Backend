package com.example.mtc.repository;

import com.example.mtc.entity.BookWorld;
import com.example.mtc.entity.BookWorldId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookWorldRepository
        extends JpaRepository<BookWorld, BookWorldId> {

    List<BookWorld> findByIdBookId(Long bookId);

    List<BookWorld> findByIdWorldId(Long worldId);
}