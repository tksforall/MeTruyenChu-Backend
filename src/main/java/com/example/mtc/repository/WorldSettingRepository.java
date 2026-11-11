package com.example.mtc.repository;

import com.example.mtc.entity.WorldSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorldSettingRepository
        extends JpaRepository<WorldSetting, Long> {
}