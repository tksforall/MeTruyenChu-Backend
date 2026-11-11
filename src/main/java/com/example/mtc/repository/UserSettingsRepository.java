package com.example.mtc.repository;

import com.example.mtc.entity.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsRepository
        extends JpaRepository<UserSettings, Long> {
}