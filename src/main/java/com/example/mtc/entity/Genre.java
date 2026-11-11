package com.example.mtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "GENRES")
public class Genre {

    @Id
    @Column(name = "GENRE_ID")
    private Long genreId;

    @Column(name = "GENRE_NAME", nullable = false, unique = true)
    private String genreName;

    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private List<BookGenre> bookGenres;

    public Genre() {
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<BookGenre> getBookGenres() {
        return bookGenres;
    }

    public void setBookGenres(List<BookGenre> bookGenres) {
        this.bookGenres = bookGenres;
    }
}