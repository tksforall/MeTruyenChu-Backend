package com.example.mtc.dto;

import java.util.List;

public class AiSearchResponse {

    private String summary;

    private List<String> keywords;

    private List<String> genres;

    public AiSearchResponse() {
    }

    public AiSearchResponse(
            String summary,
            List<String> keywords,
            List<String> genres
    ) {
        this.summary = summary;
        this.keywords = keywords;
        this.genres = genres;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}