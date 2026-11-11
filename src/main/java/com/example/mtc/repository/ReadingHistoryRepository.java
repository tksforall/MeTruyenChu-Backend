package com.example.mtc.repository;

import com.example.mtc.entity.ReadingHistory;
import com.example.mtc.entity.ReadingHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingHistoryRepository
        extends JpaRepository<
        ReadingHistory,
        ReadingHistoryId> {

    List<ReadingHistory> findByUserId(
            Long userId
    );
}