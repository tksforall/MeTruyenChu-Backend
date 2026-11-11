package com.example.mtc.service;

import com.example.mtc.entity.BookFollow;
import com.example.mtc.repository.BookFollowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookFollowService {

    private final BookFollowRepository repository;

    public BookFollowService(
            BookFollowRepository repository
    ) {
        this.repository = repository;
    }

    public List<BookFollow> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public BookFollow follow(
            Long userId,
            Long bookId
    ) {

        if (repository.existsByUserIdAndBookId(
                userId,
                bookId
        )) {
            throw new RuntimeException(
                    "User đã theo dõi truyện này"
            );
        }

        BookFollow follow = new BookFollow();

        follow.setUserId(userId);
        follow.setBookId(bookId);
        follow.setCreatedAt(LocalDateTime.now());

        return repository.save(follow);
    }

    public void unfollow(
            Long userId,
            Long bookId
    ) {

        repository.deleteByUserIdAndBookId(
                userId,
                bookId
        );
    }
}