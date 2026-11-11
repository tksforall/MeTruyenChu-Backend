package com.example.mtc.repository;

import com.example.mtc.entity.BookPersonality;
import com.example.mtc.entity.BookPersonalityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookPersonalityRepository
        extends JpaRepository<BookPersonality, BookPersonalityId> {

    List<BookPersonality> findByIdBookId(Long bookId);

    List<BookPersonality> findByIdPersonalityId(Long personalityId);
}