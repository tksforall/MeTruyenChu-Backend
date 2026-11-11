package com.example.mtc.entity;

import java.io.Serializable;
import java.util.Objects;

public class ReadingHistoryId implements Serializable {

    private Long userId;
    private Long bookId;

    public ReadingHistoryId() {
    }

    public ReadingHistoryId(
            Long userId,
            Long bookId
    ) {
        this.userId = userId;
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof ReadingHistoryId)) return false;

        ReadingHistoryId that =
                (ReadingHistoryId) o;

        return Objects.equals(userId, that.userId)
                && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId);
    }
}