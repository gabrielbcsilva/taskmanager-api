package io.gabriel.taskmanager.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.gabriel.taskmanager.model.entity.User;

import java.sql.Timestamp;
import java.util.UUID;

public class UserResponse {

    private final UUID id;
    private final String name;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }


    @JsonProperty("createdAt")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("updatedAt")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
