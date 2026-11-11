package com.example.mtc.entity;

import java.io.Serializable;
import java.util.Objects;

public class BookFollowId implements Serializable {

    private Long userId;
    private Long bookId;

    public BookFollowId() {
    }

    public BookFollowId(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof BookFollowId)) return false;

        BookFollowId that = (BookFollowId) o;

        return Objects.equals(userId, that.userId)
                && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId);
    }
}