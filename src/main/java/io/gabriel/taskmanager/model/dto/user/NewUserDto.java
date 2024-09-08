package io.gabriel.taskmanager.model.dto.user;

import io.gabriel.taskmanager.model.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "New user dto schema")
public class NewUserDto {

    @NotBlank(message = "O nome do usuário deve ser informado.")
    @Schema(description = "user name", example = "Gabriel", nullable = false)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User transformToEntity(NewUserDto newUserDto) {
        User user = new User();

        user.setName(newUserDto.getName());
        return user;
    }
}
