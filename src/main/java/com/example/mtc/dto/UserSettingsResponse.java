package com.example.mtc.dto;

import java.math.BigDecimal;

public class UserSettingsResponse {

    private Long userId;

    private String backgroundColor;

    private String nightBackgroundColor;

    private String nightMode;

    private String fontFamily;

    private BigDecimal fontSize;

    private String audioSource;

    private String voice;

    private BigDecimal speechSpeed;

    private BigDecimal pitch;


    public UserSettingsResponse(
            Long userId,
            String backgroundColor,
            String nightBackgroundColor,
            String nightMode,
            String fontFamily,
            BigDecimal fontSize,
            String audioSource,
            String voice,
            BigDecimal speechSpeed,
            BigDecimal pitch
    ) {

        this.userId = userId;
        this.backgroundColor = backgroundColor;
        this.nightBackgroundColor = nightBackgroundColor;
        this.nightMode = nightMode;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.audioSource = audioSource;
        this.voice = voice;
        this.speechSpeed = speechSpeed;
        this.pitch = pitch;
    }


    public Long getUserId() {
        return userId;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getNightBackgroundColor() {
        return nightBackgroundColor;
    }

    public String getNightMode() {
        return nightMode;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public BigDecimal getFontSize() {
        return fontSize;
    }

    public String getAudioSource() {
        return audioSource;
    }

    public String getVoice() {
        return voice;
    }

    public BigDecimal getSpeechSpeed() {
        return speechSpeed;
    }

    public BigDecimal getPitch() {
        return pitch;
    }
}