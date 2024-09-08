package io.gabriel.taskmanager.model.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.gabriel.taskmanager.model.entity.User;

import java.util.UUID;

public class ResponsibleResponse {

    private final UUID id;
    private final String name;
//    private final String email;
//    private final String profile;
//    private final String picturePath;

    public ResponsibleResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
//        this.email = user.getEmail();
//        this.profile = user.getProfile();
//        this.picturePath = user.getPicturePath();
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

//    @JsonProperty("email")
//    public String getEmail() {
//        return email;
//    }
//
//    @JsonProperty("profile")
//    public String getProfile() {
//        return profile;
//    }
//
//    @JsonProperty("picturePath")
//    public String getPicturePath() {
//        return picturePath;
//    }
}
