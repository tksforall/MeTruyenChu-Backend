package com.example.mtc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "USER_SETTINGS")
public class UserSettings {

    // ======================================================
    // USER ID
    // ======================================================

    @Id
    @Column(name = "USER_ID", nullable = false)
    private Long userId;


    // ======================================================
    // READER BACKGROUND
    // ======================================================

    @Column(name = "BACKGROUND_COLOR", length = 30)
    private String backgroundColor;

    @Column(name = "NIGHT_BACKGROUND_COLOR", length = 30)
    private String nightBackgroundColor;

    @Column(name = "NIGHT_MODE", length = 10)
    private String nightMode;


    // ======================================================
    // FONT
    // ======================================================

    @Column(name = "FONT_FAMILY", length = 100)
    private String fontFamily;

    @Column(name = "FONT_SIZE", precision = 5, scale = 2)
    private BigDecimal fontSize;


    // ======================================================
    // AUDIO
    // ======================================================

    @Column(name = "AUDIO_SOURCE", length = 500)
    private String audioSource;

    @Column(name = "VOICE", length = 100)
    private String voice;

    @Column(name = "SPEECH_SPEED", precision = 5, scale = 2)
    private BigDecimal speechSpeed;

    @Column(name = "PITCH", precision = 5, scale = 2)
    private BigDecimal pitch;


    // ======================================================
    // CONSTRUCTOR
    // ======================================================

    public UserSettings() {
    }


    // ======================================================
    // GETTER / SETTER
    // ======================================================

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public String getNightBackgroundColor() {
        return nightBackgroundColor;
    }

    public void setNightBackgroundColor(String nightBackgroundColor) {
        this.nightBackgroundColor = nightBackgroundColor;
    }


    public String getNightMode() {
        return nightMode;
    }

    public void setNightMode(String nightMode) {
        this.nightMode = nightMode;
    }


    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }


    public BigDecimal getFontSize() {
        return fontSize;
    }

    public void setFontSize(BigDecimal fontSize) {
        this.fontSize = fontSize;
    }


    public String getAudioSource() {
        return audioSource;
    }

    public void setAudioSource(String audioSource) {
        this.audioSource = audioSource;
    }


    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }


    public BigDecimal getSpeechSpeed() {
        return speechSpeed;
    }

    public void setSpeechSpeed(BigDecimal speechSpeed) {
        this.speechSpeed = speechSpeed;
    }


    public BigDecimal getPitch() {
        return pitch;
    }

    public void setPitch(BigDecimal pitch) {
        this.pitch = pitch;
    }
}