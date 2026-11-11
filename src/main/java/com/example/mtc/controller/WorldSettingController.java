package com.example.mtc.controller;

import com.example.mtc.entity.WorldSetting;
import com.example.mtc.service.WorldSettingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worlds")
public class WorldSettingController {

    private final WorldSettingService worldSettingService;

    public WorldSettingController(
            WorldSettingService worldSettingService
    ) {
        this.worldSettingService = worldSettingService;
    }

    // GET /api/worlds
    @GetMapping
    public List<WorldSetting> getAllWorlds() {
        return worldSettingService.getAllWorlds();
    }

    // GET /api/worlds/{id}
    @GetMapping("/{id}")
    public WorldSetting getWorldById(
            @PathVariable Long id
    ) {
        return worldSettingService.getWorldById(id);
    }
}