package com.example.mtc.controller;

import com.example.mtc.dto.UserSettingsRequest;
import com.example.mtc.dto.UserSettingsResponse;
import com.example.mtc.security.CurrentUserService;
import com.example.mtc.service.UserSettingsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/me/settings")
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

    private final CurrentUserService currentUserService;


    public UserSettingsController(
            UserSettingsService userSettingsService,
            CurrentUserService currentUserService
    ) {

        this.userSettingsService = userSettingsService;
        this.currentUserService = currentUserService;
    }


    // ======================================================
    // GET SETTINGS
    // ======================================================

    @GetMapping
    public UserSettingsResponse getSettings() {

        Long userId =
                currentUserService.getCurrentUserId();

        return userSettingsService.getSettings(userId);
    }


    // ======================================================
    // UPDATE SETTINGS
    // ======================================================

    @PutMapping
    public UserSettingsResponse updateSettings(
            @RequestBody UserSettingsRequest request
    ) {
        System.out.println("========== UPDATE SETTINGS ==========");

        System.out.println("backgroundColor = [" + request.getBackgroundColor() + "]");
        System.out.println("nightBackgroundColor = [" + request.getNightBackgroundColor() + "]");
        System.out.println("nightMode = [" + request.getNightMode() + "]");
        System.out.println("fontFamily = [" + request.getFontFamily() + "]");
        System.out.println("fontSize = [" + request.getFontSize() + "]");

        Long userId =
                currentUserService.getCurrentUserId();

        return userSettingsService.updateSettings(
                userId,
                request
        );
    }
}