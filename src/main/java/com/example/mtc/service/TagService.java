package com.example.mtc.service;

import com.example.mtc.entity.Tag;
import com.example.mtc.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // Lấy tất cả tag
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    // Lấy tag theo ID
    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy tag với ID: " + id)
                );
    }
}