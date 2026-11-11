package com.example.mtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "BOOK_GENRES")
public class BookGenre {

    @EmbeddedId
    private BookGenreId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "BOOK_ID")
    @JsonIgnore
    private Book book;

    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    public BookGenre() {
    }

    public BookGenreId getId() {
        return id;
    }

    public void setId(BookGenreId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}