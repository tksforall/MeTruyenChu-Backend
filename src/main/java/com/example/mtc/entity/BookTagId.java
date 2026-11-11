package com.example.mtc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookTagId implements Serializable {

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "TAG_ID")
    private Long tagId;

    public BookTagId() {
    }

    public BookTagId(Long bookId, Long tagId) {
        this.bookId = bookId;
        this.tagId = tagId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof BookTagId)) return false;

        BookTagId that = (BookTagId) o;

        return Objects.equals(bookId, that.bookId)
                && Objects.equals(tagId, that.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, tagId);
    }
}