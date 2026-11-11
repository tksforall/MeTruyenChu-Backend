package com.example.mtc.service;

import com.example.mtc.dto.UserSettingsRequest;
import com.example.mtc.dto.UserSettingsResponse;
import com.example.mtc.entity.UserSettings;
import com.example.mtc.repository.UserSettingsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserSettingsService {

    private final UserSettingsRepository repository;


    public UserSettingsService(
            UserSettingsRepository repository
    ) {
        this.repository = repository;
    }


    // ======================================================
    // LẤY SETTINGS
    // ======================================================

    public UserSettingsResponse getSettings(Long userId) {

        UserSettings settings =
                repository.findById(userId)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Không tìm thấy cài đặt người dùng"
                                )
                        );

        return toResponse(settings);
    }


    // ======================================================
    // CẬP NHẬT SETTINGS
    // ======================================================

    public UserSettingsResponse updateSettings(
            Long userId,
            UserSettingsRequest request
    ) {

        UserSettings settings =
                repository.findById(userId)
                        .orElseGet(() -> {

                            UserSettings newSettings =
                                    new UserSettings();

                            newSettings.setUserId(userId);

                            return newSettings;
                        });


        // ==================================================
        // READER BACKGROUND
        // ==================================================

        if (request.getBackgroundColor() != null) {

            settings.setBackgroundColor(
                    request.getBackgroundColor()
            );
        }


        if (request.getNightBackgroundColor() != null) {

            settings.setNightBackgroundColor(
                    request.getNightBackgroundColor()
            );
        }


        if (request.getNightMode() != null) {

            String mode =
                    request.getNightMode().toUpperCase();

            if (!mode.equals("AUTO")
                    && !mode.equals("OFF")
                    && !mode.equals("ON")) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Chế độ ban đêm không hợp lệ"
                );
            }

            settings.setNightMode(mode);
        }


        // ==================================================
        // FONT
        // ==================================================

        if (request.getFontFamily() != null) {

            settings.setFontFamily(
                    request.getFontFamily()
            );
        }


        if (request.getFontSize() != null) {

            settings.setFontSize(
                    request.getFontSize()
            );
        }


        // ==================================================
        // AUDIO
        // ==================================================

        if (request.getAudioSource() != null) {

            settings.setAudioSource(
                    request.getAudioSource()
            );
        }


        if (request.getVoice() != null) {

            settings.setVoice(
                    request.getVoice()
            );
        }


        if (request.getSpeechSpeed() != null) {

            settings.setSpeechSpeed(
                    request.getSpeechSpeed()
            );
        }


        if (request.getPitch() != null) {

            settings.setPitch(
                    request.getPitch()
            );
        }


        System.out.println("========== BEFORE SAVE ==========");

        UserSettings saved = repository.save(settings);

        System.out.println("========== AFTER SAVE ==========");

        System.out.println("saved userId = " + saved.getUserId());
        System.out.println("saved backgroundColor = " + saved.getBackgroundColor());
        System.out.println("saved nightBackgroundColor = " + saved.getNightBackgroundColor());
        System.out.println("saved nightMode = " + saved.getNightMode());

        System.out.println("========== BEFORE RESPONSE ==========");

        UserSettingsResponse response = toResponse(saved);

        System.out.println("========== AFTER RESPONSE ==========");

        return response;
    }


    // ======================================================
    // ENTITY → RESPONSE
    // ======================================================

    private UserSettingsResponse toResponse(
            UserSettings settings
    ) {

        return new UserSettingsResponse(
                settings.getUserId(),
                settings.getBackgroundColor(),
                settings.getNightBackgroundColor(),
                settings.getNightMode(),
                settings.getFontFamily(),
                settings.getFontSize(),
                settings.getAudioSource(),
                settings.getVoice(),
                settings.getSpeechSpeed(),
                settings.getPitch()
        );
    }
}