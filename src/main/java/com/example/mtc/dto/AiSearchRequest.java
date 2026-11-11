package com.example.mtc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AiSearchRequest {

    @NotBlank(message = "Mô tả truyện không được để trống")
    @Size(max = 5000, message = "Mô tả truyện không được vượt quá 5000 ký tự")
    private String description;

    public AiSearchRequest() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}