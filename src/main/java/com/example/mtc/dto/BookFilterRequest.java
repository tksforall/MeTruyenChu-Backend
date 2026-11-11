package com.example.mtc.dto;

import java.util.HashSet;
import java.util.Set;

public class BookFilterRequest {

    // ==================================================
    // CHỌN 1
    // ==================================================

    private String sort;

    private String status;

    private String chapterRange;

    private String publishDate;


    // ==================================================
    // CHỌN NHIỀU
    // ==================================================

    private Set<String> genres = new HashSet<>();

    private Set<String> personalities = new HashSet<>();

    private Set<String> worlds = new HashSet<>();

    private Set<String> tags = new HashSet<>();


    // ==================================================
    // GETTER / SETTER
    // ==================================================

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getChapterRange() {
        return chapterRange;
    }

    public void setChapterRange(String chapterRange) {
        this.chapterRange = chapterRange;
    }


    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }


    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }


    public Set<String> getPersonalities() {
        return personalities;
    }

    public void setPersonalities(Set<String> personalities) {
        this.personalities = personalities;
    }


    public Set<String> getWorlds() {
        return worlds;
    }

    public void setWorlds(Set<String> worlds) {
        this.worlds = worlds;
    }


    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}