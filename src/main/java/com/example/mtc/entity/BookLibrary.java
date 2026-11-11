package com.example.mtc.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOK_LIBRARY")
@IdClass(BookLibraryId.class)
public class BookLibrary {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Id
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    public BookLibrary() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}