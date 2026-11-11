package com.example.mtc.entity;

import java.io.Serializable;
import java.util.Objects;

public class BookLibraryId implements Serializable {

    private Long userId;
    private Long bookId;

    public BookLibraryId() {
    }

    public BookLibraryId(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof BookLibraryId)) return false;

        BookLibraryId that = (BookLibraryId) o;

        return Objects.equals(userId, that.userId)
                && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId);
    }
}