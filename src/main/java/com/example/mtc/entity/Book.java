package com.example.mtc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.util.List;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COVER_URL")
    private String coverUrl;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CHAPTER_COUNT")
    private Integer chapterCount;

    @Column(name = "VIEW_COUNT")
    private Long viewCount;

    @Column(name = "WEEKLY_VIEW_COUNT")
    private Long weeklyViewCount;

    @Column(name = "RECOMMENDATION_COUNT")
    private Long recommendationCount;

    @Column(name = "WEEKLY_RECOMMENDATION_COUNT")
    private Long weeklyRecommendationCount;

    @Column(name = "COMMENT_COUNT")
    private Long commentCount;

    @Column(name = "BOOKMARK_COUNT")
    private Long bookmarkCount;

    @Column(name = "RATING_COUNT")
    private Long ratingCount;

    @Column(name = "RATING_SCORE")
    private Double ratingScore;



    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "book")
    private List<BookGenre> bookGenres;

    @OneToMany(mappedBy = "book")
    private List<BookPersonality> bookPersonalities;

    @OneToMany(mappedBy = "book")
    private List<BookWorld> bookWorlds;

    @OneToMany(mappedBy = "book")
    private List<BookTag> bookTags;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(Integer chapterCount) {
        this.chapterCount = chapterCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getWeeklyViewCount() {
        return weeklyViewCount;
    }

    public void setWeeklyViewCount(Long weeklyViewCount) {
        this.weeklyViewCount = weeklyViewCount;
    }

    public Long getRecommendationCount() {
        return recommendationCount;
    }

    public void setRecommendationCount(Long recommendationCount) {
        this.recommendationCount = recommendationCount;
    }

    public Long getWeeklyRecommendationCount() {
        return weeklyRecommendationCount;
    }

    public void setWeeklyRecommendationCount(Long weeklyRecommendationCount) {
        this.weeklyRecommendationCount = weeklyRecommendationCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getBookmarkCount() {
        return bookmarkCount;
    }

    public void setBookmarkCount(Long bookmarkCount) {
        this.bookmarkCount = bookmarkCount;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(Double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public List<BookGenre> getBookGenres() {
        return bookGenres;
    }

    public void setBookGenres(List<BookGenre> bookGenres) {
        this.bookGenres = bookGenres;
    }

    public List<BookPersonality> getBookPersonalities() {
        return bookPersonalities;
    }

    public void setBookPersonalities(
            List<BookPersonality> bookPersonalities
    ) {
        this.bookPersonalities = bookPersonalities;
    }


    public List<BookWorld> getBookWorlds() {
        return bookWorlds;
    }

    public void setBookWorlds(
            List<BookWorld> bookWorlds
    ) {
        this.bookWorlds = bookWorlds;
    }


    public List<BookTag> getBookTags() {
        return bookTags;
    }

    public void setBookTags(
            List<BookTag> bookTags
    ) {
        this.bookTags = bookTags;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


}