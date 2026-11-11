package com.example.mtc.service;

import com.example.mtc.entity.WorldSetting;
import com.example.mtc.repository.WorldSettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorldSettingService {

    private final WorldSettingRepository worldSettingRepository;

    public WorldSettingService(
            WorldSettingRepository worldSettingRepository
    ) {
        this.worldSettingRepository = worldSettingRepository;
    }

    // Lấy tất cả World
    public List<WorldSetting> getAllWorlds() {
        return worldSettingRepository.findAll();
    }

    // Lấy World theo ID
    public WorldSetting getWorldById(Long id) {

        return worldSettingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy World với ID: " + id
                        )
                );
    }
}