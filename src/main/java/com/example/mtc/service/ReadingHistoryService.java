package com.example.mtc.service;

import com.example.mtc.entity.ReadingHistory;
import com.example.mtc.entity.ReadingHistoryId;
import com.example.mtc.repository.ReadingHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReadingHistoryService {

    private final ReadingHistoryRepository repository;

    public ReadingHistoryService(
            ReadingHistoryRepository repository
    ) {
        this.repository = repository;
    }

    // ======================================================
    // LẤY LỊCH SỬ ĐỌC CỦA USER
    // ======================================================

    public List<ReadingHistory> getByUserId(
            Long userId
    ) {

        return repository.findByUserId(userId);
    }


    // ======================================================
    // LƯU / UPDATE TIẾN ĐỘ ĐỌC
    // ======================================================

    public ReadingHistory saveHistory(
            Long userId,
            Long bookId,
            Long chapterId,
            Double progress
    ) {

        ReadingHistoryId id =
                new ReadingHistoryId(
                        userId,
                        bookId
                );

        ReadingHistory history =
                repository.findById(id)
                        .orElseGet(
                                ReadingHistory::new
                        );

        history.setUserId(userId);
        history.setBookId(bookId);
        history.setChapterId(chapterId);
        history.setProgress(progress);
        history.setLastReadAt(
                LocalDateTime.now()
        );

        return repository.save(history);
    }
}