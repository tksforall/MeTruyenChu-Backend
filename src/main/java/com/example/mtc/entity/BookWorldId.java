package com.example.mtc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookWorldId implements Serializable {

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "WORLD_ID")
    private Long worldId;

    public BookWorldId() {
    }

    public BookWorldId(Long bookId, Long worldId) {
        this.bookId = bookId;
        this.worldId = worldId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getWorldId() {
        return worldId;
    }

    public void setWorldId(Long worldId) {
        this.worldId = worldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookWorldId)) return false;

        BookWorldId that = (BookWorldId) o;

        return Objects.equals(bookId, that.bookId)
                && Objects.equals(worldId, that.worldId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, worldId);
    }
}