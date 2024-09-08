package io.gabriel.taskmanager.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "update user dto schema")
public class UpdateUserDto {

    @NotNull(message = "O id do usuário deve ser informado.")
    @Schema(description = "User id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @NotBlank(message = "O nome do usuário deve ser informado.")
    @Schema(description = "user name", example = "Gabriel", nullable = false)
    private String name;


    public UUID getId() { return id; }

    public String getName() {
        return name;
    }

    public void setId(UUID id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

}
