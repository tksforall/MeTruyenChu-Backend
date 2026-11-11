package com.example.mtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "BOOK_WORLDS")
public class BookWorld {

    @EmbeddedId
    private BookWorldId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "BOOK_ID")
    @JsonIgnore
    private Book book;

    @ManyToOne
    @MapsId("worldId")
    @JoinColumn(name = "WORLD_ID")
    private WorldSetting world;

    public BookWorld() {
    }

    public BookWorld(BookWorldId id) {
        this.id = id;
    }

    public BookWorldId getId() {
        return id;
    }

    public void setId(BookWorldId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public WorldSetting getWorld() {
        return world;
    }

    public void setWorld(WorldSetting world) {
        this.world = world;
    }
}