package com.example.mtc.dto;

import java.math.BigDecimal;

public class UserSettingsRequest {

    private String backgroundColor;

    private String nightBackgroundColor;

    private String nightMode;

    private String fontFamily;

    private BigDecimal fontSize;

    private String audioSource;

    private String voice;

    private BigDecimal speechSpeed;

    private BigDecimal pitch;


    public UserSettingsRequest() {
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