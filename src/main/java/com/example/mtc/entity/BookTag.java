package com.example.mtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "BOOK_TAGS")
public class BookTag {

    @EmbeddedId
    private BookTagId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "BOOK_ID")
    @JsonIgnore
    private Book book;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    public BookTag() {
    }

    public BookTagId getId() {
        return id;
    }

    public void setId(BookTagId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}