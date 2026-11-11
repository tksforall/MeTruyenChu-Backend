package com.example.mtc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookGenreId implements Serializable {

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "GENRE_ID")
    private Long genreId;

    public BookGenreId() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}