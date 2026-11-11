package com.example.mtc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookPersonalityId implements Serializable {

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "PERSONALITY_ID")
    private Long personalityId;

    public BookPersonalityId() {
    }

    public BookPersonalityId(Long bookId, Long personalityId) {
        this.bookId = bookId;
        this.personalityId = personalityId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getPersonalityId() {
        return personalityId;
    }

    public void setPersonalityId(Long personalityId) {
        this.personalityId = personalityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookPersonalityId)) return false;

        BookPersonalityId that = (BookPersonalityId) o;

        return Objects.equals(bookId, that.bookId)
                && Objects.equals(personalityId, that.personalityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, personalityId);
    }
}