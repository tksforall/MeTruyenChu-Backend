package com.example.mtc.repository;

import com.example.mtc.entity.Book;

import java.util.List;
import java.util.Set;

public interface BookFilterRepository {

    List<Book> filterBooks(
            String sort,
            String status,
            String chapterRange,
            String publishDate,
            Set<String> genres,
            Set<String> personalities,
            Set<String> worlds,
            Set<String> tags
    );
}