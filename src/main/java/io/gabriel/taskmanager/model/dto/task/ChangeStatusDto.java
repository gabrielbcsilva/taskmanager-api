package io.gabriel.taskmanager.model.dto.task;

import io.gabriel.taskmanager.infra.notation.StatusValidator;
import io.gabriel.taskmanager.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Change task status dto schema")
public class ChangeStatusDto {

    @NotNull(message = "O id da tarefa deve ser informado.")
    @Schema(description = "Task id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID taskId;

    @NotBlank(message = "O status da tarefa deve ser informado.")
    @StatusValidator(enumClass = Status.class, message = "Informe um status válido.")
    @Schema(description = "Task status", example = "in progress")
    private String status;

    public UUID getTaskId() {
        return this.taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
