package com.example.mtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "BOOK_PERSONALITIES")
public class BookPersonality {

    @EmbeddedId
    private BookPersonalityId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "BOOK_ID")
    @JsonIgnore
    private Book book;

    @ManyToOne
    @MapsId("personalityId")
    @JoinColumn(name = "PERSONALITY_ID")
    private Personality personality;

    public BookPersonality() {
    }

    public BookPersonality(BookPersonalityId id) {
        this.id = id;
    }

    public BookPersonalityId getId() {
        return id;
    }

    public void setId(BookPersonalityId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Personality getPersonality() {
        return personality;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
    }
}