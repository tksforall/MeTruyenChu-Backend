package com.example.mtc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "WORLD_SETTINGS")
public class WorldSetting {

    @Id
    @Column(name = "WORLD_ID")
    private Long worldId;

    @Column(name = "WORLD_NAME", nullable = false, unique = true)
    private String worldName;

    public WorldSetting() {
    }

    public Long getWorldId() {
        return worldId;
    }

    public void setWorldId(Long worldId) {
        this.worldId = worldId;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }
}