package com.example.mtc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PERSONALITIES")
public class Personality {

    @Id
    @Column(name = "PERSONALITY_ID")
    private Long personalityId;

    @Column(name = "PERSONALITY_NAME", nullable = false, unique = true)
    private String personalityName;

    public Personality() {
    }

    public Long getPersonalityId() {
        return personalityId;
    }

    public void setPersonalityId(Long personalityId) {
        this.personalityId = personalityId;
    }

    public String getPersonalityName() {
        return personalityName;
    }

    public void setPersonalityName(String personalityName) {
        this.personalityName = personalityName;
    }
}