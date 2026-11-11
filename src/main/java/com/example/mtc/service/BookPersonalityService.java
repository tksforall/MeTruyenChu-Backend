package com.example.mtc.service;

import com.example.mtc.entity.BookPersonality;
import com.example.mtc.entity.BookPersonalityId;
import com.example.mtc.repository.BookPersonalityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookPersonalityService {

    private final BookPersonalityRepository bookPersonalityRepository;

    public BookPersonalityService(
            BookPersonalityRepository bookPersonalityRepository
    ) {
        this.bookPersonalityRepository = bookPersonalityRepository;
    }

    public List<BookPersonality> getAllBookPersonalities() {
        return bookPersonalityRepository.findAll();
    }

    public List<BookPersonality> getPersonalitiesByBookId(Long bookId) {
        return bookPersonalityRepository.findByIdBookId(bookId);
    }

    public List<BookPersonality> getBooksByPersonalityId(Long personalityId) {
        return bookPersonalityRepository
                .findByIdPersonalityId(personalityId);
    }

    public BookPersonality addBookPersonality(
            Long bookId,
            Long personalityId
    ) {
        BookPersonalityId id =
                new BookPersonalityId(bookId, personalityId);

        return bookPersonalityRepository.save(
                new BookPersonality(id)
        );
    }

    public void deleteBookPersonality(
            Long bookId,
            Long personalityId
    ) {
        BookPersonalityId id =
                new BookPersonalityId(bookId, personalityId);

        bookPersonalityRepository.deleteById(id);
    }
}