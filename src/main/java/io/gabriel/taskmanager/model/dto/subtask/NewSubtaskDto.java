package io.gabriel.taskmanager.model.dto.subtask;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "New subtask dto schema")
public class NewSubtaskDto {

    @NotBlank(message = "O título da subtarefa deve ser informado.")
    @Schema(description = "Subtask title", example = "Criar página de login", nullable = false)
    private String title;

    @NotNull(message = "O id da tarefa deve ser informado.")
    @Schema(description = "Task id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", nullable = false)
    private UUID taskId;

    public String getTitle() {
        return title;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }
}
