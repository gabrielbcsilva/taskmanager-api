package io.gabriel.taskmanager.model.dto.subtask;

import io.gabriel.taskmanager.infra.notation.StatusValidator;
import io.gabriel.taskmanager.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Change subtask status dto schema")
public class ChangeStatusDto {

    @NotNull(message = "O id da subtarefa deve ser informado.")
    @Schema(description = "Subtask id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID subtaskId;

    @NotBlank(message = "O status da tarefa deve ser informado.")
    @StatusValidator(enumClass = Status.class)
    @Schema(description = "Task status", example = "in progress")
    private String status;

    public UUID getSubtaskId() {
        return this.subtaskId;
    }

    public String getStatus() {
        return status;
    }

    public void setSubtaskId(UUID subtaskId) {
        this.subtaskId = subtaskId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
