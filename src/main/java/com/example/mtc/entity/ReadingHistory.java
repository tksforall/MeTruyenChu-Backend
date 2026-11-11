package com.example.mtc.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "READING_HISTORY")
@IdClass(ReadingHistoryId.class)
public class ReadingHistory {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Id
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "CHAPTER_ID", nullable = false)
    private Long chapterId;

    @Column(name = "LAST_READ_AT", nullable = false)
    private LocalDateTime lastReadAt;

    @Column(name = "PROGRESS", nullable = false)
    private Double progress = 0.0;

    public ReadingHistory() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public LocalDateTime getLastReadAt() {
        return lastReadAt;
    }

    public void setLastReadAt(LocalDateTime lastReadAt) {
        this.lastReadAt = lastReadAt;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }
}