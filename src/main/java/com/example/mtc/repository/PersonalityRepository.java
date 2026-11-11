package com.example.mtc.repository;

import com.example.mtc.entity.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityRepository
        extends JpaRepository<Personality, Long> {
}