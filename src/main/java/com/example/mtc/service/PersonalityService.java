package com.example.mtc.service;

import com.example.mtc.entity.Personality;
import com.example.mtc.repository.PersonalityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalityService {

    private final PersonalityRepository personalityRepository;

    public PersonalityService(
            PersonalityRepository personalityRepository
    ) {
        this.personalityRepository = personalityRepository;
    }

    // Lấy tất cả Personality
    public List<Personality> getAllPersonalities() {
        return personalityRepository.findAll();
    }

    // Lấy Personality theo ID
    public Personality getPersonalityById(Long id) {

        return personalityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy Personality với ID: " + id
                        )
                );
    }
}