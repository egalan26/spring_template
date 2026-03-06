package com.helloyou.shared.domain.models;

import java.util.UUID;

public class BaseModel {
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
